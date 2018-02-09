package br.com.ramir.users.endpoints;

import br.com.ramir.users.security.Credential;
import br.com.ramir.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/login")
public class LoginController {

    private UserService userService;

    @Autowired
    private Credential credential;

    @GetMapping
    public ModelAndView loginPage(Model model) {
        return new ModelAndView("login");
    }

    @PostMapping
    public String login(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password){

        try{

            Authentication auth = new UsernamePasswordAuthenticationToken(login,password);
            SecurityContextHolder.getContext().setAuthentication(auth);

        }catch (Exception ex){
            ex.printStackTrace();
        }



        return "LOGGED";
    }
}