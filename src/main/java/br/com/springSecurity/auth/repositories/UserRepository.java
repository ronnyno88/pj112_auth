package br.com.springSecurity.auth.repositories;

import br.com.springSecurity.auth.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
