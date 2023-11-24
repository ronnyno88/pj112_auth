package br.com.springSecurity.auth.services;

import br.com.springSecurity.auth.DTOs.UserDTORequest;
import br.com.springSecurity.auth.domains.User;
import br.com.springSecurity.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    @Autowired
    UserRepository repository;

    public User saveUser(UserDTORequest userDTO){
        User user = new User(userDTO);
        return repository.save(user);
    }

    public List<User> findUserAll(){
        return repository.findAll();
    }

    public Optional<User> findUserOne(Integer id){
        return repository.findById(id);
    }
}
