package br.com.springSecurity.auth.controllers;

import br.com.springSecurity.auth.DTOs.UserDTORequest;
import br.com.springSecurity.auth.domains.User;
import br.com.springSecurity.auth.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthController {

	@Autowired
	UserService service;

	@PostMapping("/user")
	public ResponseEntity<User> saveUser(@RequestBody @Valid UserDTORequest user){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.saveUser(user));
	}
	@GetMapping("/user")
	public ResponseEntity<List<User>> listAllUser() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findUserAll());
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<Optional<User>> listOneUser(@PathVariable(value = "id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findUserOne(id));
	}
}
