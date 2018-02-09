package br.com.ramir.users.security;

import br.com.ramir.users.model.User;
import br.com.ramir.users.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AuthProviderService implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Credential credential;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findByLoginAndPassword(login,password);

        if(user != null){

            credential.setUser(user);

            return new UsernamePasswordAuthenticationToken(login,password,new ArrayList<>());
        }

        throw new UsernameNotFoundException("Login e/ou Senha inválidos.");

    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
