package br.com.ramir.users.endpoints;

import br.com.ramir.users.model.User;
import br.com.ramir.users.repo.UserRepository;
import br.com.ramir.users.security.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Secured("ROLE_USER")
    @GetMapping("{id}")
    public String find(@PathVariable Long id){
        User user = userRepository.findOne(id);
        return user.getName();
    }

    @Secured("ROLE_USER")
    @GetMapping("list")
    public Iterable<User> list(){
        return userRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Valid @RequestBody User user){
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();

        user.setCreatedDate(new Date());
        userRepository.save(user);
        return "User Created with ID :"+user.getId().toString();
    }

    @Secured("ROLE_USER")
    @PutMapping
    public String update(@RequestBody User user){
        User userRepo = userRepository.findOne(user.getId());
        if(!user.getPassword().equals(userRepo.getPassword())){
            if(!credential.getUser().getAdmin()){
                return "ERROR - Only Admins are able to change users passwords";
            }
        }
        userRepo.updateFields(user);
        userRepository.save(user);
        return "User Updated!";
    }

    @Secured("ROLE_ADMIN")
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
