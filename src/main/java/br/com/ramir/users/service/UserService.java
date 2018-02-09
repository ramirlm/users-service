package br.com.ramir.users.service;

import br.com.ramir.users.model.User;
import br.com.ramir.users.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String login, String password){

        try{

            User user =  userRepository.findByLoginAndPassword(login,password);
            return user;

        }catch(RuntimeException ex){
            ex.printStackTrace();
        }
        return null;

    }

}
