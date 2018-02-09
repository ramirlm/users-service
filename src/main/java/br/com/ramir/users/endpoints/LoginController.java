package br.com.ramir.users.endpoints;

import br.com.ramir.users.model.User;
import br.com.ramir.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView loginPage(Model model) {
        return new ModelAndView("login");
    }

    @PostMapping
    @RequestMapping()
    public String login(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password){

        try{
            User user = userService.login(login,password);

            if(user != null && user.getPassword().equals(password)){

                Authentication auth = new UsernamePasswordAuthenticationToken(login,password);
                SecurityContextHolder.getContext().setAuthentication(auth);

                return "LOGGED";
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "ERROR - USER NOT FOUND";
    }
}