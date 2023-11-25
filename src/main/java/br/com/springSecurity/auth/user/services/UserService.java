package br.com.springSecurity.auth.user.services;

import br.com.springSecurity.auth.user.domains.User;
import br.com.springSecurity.auth.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository repository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }
    public List<User> findUserAll(){
        return repository.findAll();
    }

    public Optional<User> findUserOne(Integer id){
        return repository.findById(id);
    }
}
