package br.com.ramir.users.endpoints;

import br.com.ramir.users.model.User;
import br.com.ramir.users.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String listUsers(){
        Iterable<User> users = userRepository.findAll();
        return "teste";
    }
}
