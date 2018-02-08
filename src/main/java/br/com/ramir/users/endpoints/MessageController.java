package br.com.ramir.users.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    @GetMapping("/admins")
    public String messageToAdmin() {
        return "login/login";
    }

    @GetMapping("/user/{id}")
    public String messageToUser(@PathVariable Long id) {
        return "login/login";
    }
}