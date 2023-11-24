package br.com.springSecurity.auth.domains;

import br.com.springSecurity.auth.DTOs.UserDTORequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usertest")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String role;

    public User(UserDTORequest data){
        this.name = data.name();
        this.role = data.role();
    }
}
