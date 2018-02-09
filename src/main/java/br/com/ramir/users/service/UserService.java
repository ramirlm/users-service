package br.com.ramir.users.service;

import br.com.ramir.users.model.User;
import br.com.ramir.users.repo.UserRepository;
import br.com.ramir.users.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class UserService implements UserDetailsService, Serializable {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByLogin(username);
        if(user != null){
            return new UserDetailsImpl(user);
        }
        throw new UsernameNotFoundException("User not found!");
    }
}
