package br.com.ramir.users.endpoints;

import br.com.ramir.users.model.User;
import br.com.ramir.users.repo.UserRepository;
import br.com.ramir.users.security.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Credential credential;

    @GetMapping("{id}")
    public String find(@PathVariable Long id){ //TODO Checar o usuário logado em todos os métodos
        User user = userRepository.findOne(id);
        return user.getName();
    }

    @GetMapping("list")
    public Iterable<User> list(){
        return userRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Valid @RequestBody User user){
        user.setCreatedDate(new Date());
        userRepository.save(user);
        return "User Created with ID :"+user.getId().toString();
    }

    @PutMapping
    public String update(@RequestBody User user){
        User userRepo = userRepository.findOne(user.getId());
        if(!user.getPassword().equals(userRepo.getPassword())){
            if(!credential.getUser().getAdmin()){
                return "ERROR - Only Admins are able to change users passwords";
            }
        }
        userRepository.save(user);
        return "User Updated!";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id){
        if(credential.getUser().getAdmin()) {
            userRepository.delete(id);
            return "User Deleted!";
        }else{
            return "ERROR - Only Admins are able to delete users";
        }
    }

}
