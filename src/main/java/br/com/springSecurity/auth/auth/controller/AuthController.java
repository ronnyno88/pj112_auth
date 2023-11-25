package br.com.springSecurity.auth.auth.controller;


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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token = tokenService.generateToken((User) auth.getPrincipal());
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}

	@PostMapping("/register")
	public  ResponseEntity register(@RequestBody @Valid RegisterDTO data){
		if (this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		User newUser = new User(data.login(), encryptedPassword, data.role());
		this.userRepository.save(newUser);
		return ResponseEntity.ok().build();
	}
}
