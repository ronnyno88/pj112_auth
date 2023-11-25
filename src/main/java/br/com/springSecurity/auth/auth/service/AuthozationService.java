package br.com.springSecurity.auth.auth.service;

import org.springframework.context.ApplicationContext;
import br.com.springSecurity.auth.configs.TokenService;
import br.com.springSecurity.auth.user.DTOs.AuthenticationDTO;
import br.com.springSecurity.auth.user.DTOs.LoginResponseDTO;
import br.com.springSecurity.auth.user.DTOs.RegisterDTO;
import br.com.springSecurity.auth.user.domains.User;
import br.com.springSecurity.auth.user.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
public class AuthozationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login);
    }

    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO data){
        authenticationManager = applicationContext.getBean(AuthenticationManager.class);
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    public ResponseEntity<Object> register(@RequestBody RegisterDTO data){
        if (this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());
        newUser.setCreatedAt(new Date(System.currentTimeMillis()));
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

}
