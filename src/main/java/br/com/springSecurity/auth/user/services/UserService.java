package br.com.springSecurity.auth.user.services;

import br.com.springSecurity.auth.user.DTOs.AuthenticationDTO;
import br.com.springSecurity.auth.user.DTOs.LoginResponseDTO;
import br.com.springSecurity.auth.user.DTOs.RegisterUserDTO;
import br.com.springSecurity.auth.user.domains.User;
import br.com.springSecurity.auth.user.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    public User save(RegisterUserDTO data){
        User newUser = new User(data);
        return repository.save(newUser);
    }
}
