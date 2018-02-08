package br.com.ramir.users.endpoints;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String loginPage(Model model) {
        return "login/login";
    }

    @PostMapping
    public String login(@RequestParam String login, @RequestParam String password){
        return "";
    }
}