package br.com.springSecurity.auth.user.controllers;

import br.com.springSecurity.auth.security.TokenService;
import br.com.springSecurity.auth.user.DTOs.AuthenticationDTO;
import br.com.springSecurity.auth.user.DTOs.LoginResponseDTO;
import br.com.springSecurity.auth.user.DTOs.RegisterUserDTO;
import br.com.springSecurity.auth.user.domains.User;
import br.com.springSecurity.auth.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public  ResponseEntity register(@RequestBody @Valid RegisterUserDTO data){
        if (this.userService.loadUserByUsername(data.login()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        this.userService.save(data);
        return ResponseEntity.ok().body(data);
    }
}
