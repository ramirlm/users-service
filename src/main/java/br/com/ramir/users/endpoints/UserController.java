package br.com.ramir.users.endpoints;

import br.com.ramir.users.model.User;
import br.com.ramir.users.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("{id}")
    public String find(@PathVariable Long id){
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
        userRepository.save(user);
        return user.getId().toString();
    }

    @PutMapping
    public String update(@Valid @RequestBody User user){
        //TODO SOMENTE o Admin pode trocar a senha
        userRepository.save(user);
        return user.getId().toString();
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id){
        userRepository.delete(id);
        return "Usuario deletado";
    }

}
