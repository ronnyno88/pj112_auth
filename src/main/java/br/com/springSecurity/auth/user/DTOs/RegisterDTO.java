package br.com.springSecurity.auth.user.DTOs;

import br.com.springSecurity.auth.user.domains.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
