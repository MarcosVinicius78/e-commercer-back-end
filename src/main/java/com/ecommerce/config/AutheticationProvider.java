package com.ecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommerce.entity.login.Authority;
import com.ecommerce.entity.login.User;
import com.ecommerce.repository.UserRepository;

@Component
public class AutheticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        List<User> user = userRepository.findByUsername(username);

        System.out.println("teste");

        if (user.size() > 0) {
            if (passwordEncoder.matches(password, user.get(0).getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, password,grantedAuthorities(user.get(0).getAuthorities()));
            }else{
                throw new BadCredentialsException("Senha está errada");
            }
        }else{

            // User userSave = User.builder()
            //                 .username(username)
            //                 .password(passwordEncoder.encode(password))
            //                 .build();

            // userRepository.save(userSave);

            // System.out.println(userSave);

            throw new BadCredentialsException("Nenhum usuario encontrado");
        }

    }

    private List<GrantedAuthority> grantedAuthorities(Set<Authority> authorities){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getNome()));
        }

        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication)); 
    }
    
}
