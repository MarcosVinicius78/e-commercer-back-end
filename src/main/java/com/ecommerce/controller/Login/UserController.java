package com.ecommerce.controller.Login;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.request.UserCreate;
import com.ecommerce.dto.response.ClienteResponse;
import com.ecommerce.entity.Cliente;
import com.ecommerce.entity.login.Authority;
import com.ecommerce.entity.login.User;
import com.ecommerce.repository.AuthorityRepository;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.repository.UserRepository;

@RestController
public class UserController {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/register")
    public ResponseEntity<User> registrarUsuario(@RequestBody UserCreate userCreate){

        ResponseEntity<User> response = null;
        
        if (userRepository.findByUsername(userCreate.username()).size() > 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        
        User user = new User();
        Set<Authority> role = new HashSet<>();
        
        String hashPassword = passwordEncoder.encode(userCreate.password());
        Cliente creatdCliente = new Cliente();
        
        user.setPassword(hashPassword);
        user.setUsername(userCreate.username());
        user.setCustomer(creatdCliente);
        user.setAuthorities(role);
        
        creatdCliente.setEmail(userCreate.email());
        creatdCliente.setPrimeiroNome(userCreate.primeiroNome());
        creatdCliente.setUltimoNome(userCreate.ultimoNome());
    
        customerRepository.save(creatdCliente);
        
        userRepository.save(user);
        role.add(authorityRepository.save(getAuthority(user)));
        if (userRepository.save(user).getUserId() > 0) {
            response = ResponseEntity.ok().body(user);
        }

        return response;
    }

    private Authority getAuthority(User user){

        Authority authority = new Authority();

        authority.setNome("ROLE_USER");

        authority.setUser(user);

        return authority;
    }

    @GetMapping("/api/user")
    public ResponseEntity<ClienteResponse> getCliente(Authentication authentication){
         List<User> user = userRepository.findByUsername(authentication.getName());

        return ResponseEntity.ok().body(ClienteResponse.toResponse(user.get(0).getCustomer()));
    }

}
