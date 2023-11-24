package br.com.springSecurity.auth.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTORequest(@NotBlank String name, @NotNull String role) {

}
