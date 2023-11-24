package br.com.springSecurity.auth.DTOs;

public record UserDTOResponse(Integer id, String name, String role) {
    public UserDTOResponse(Integer id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
}
