package dev.eduardo.security.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "E-mail é obrigatorio")
        String email,
        @NotEmpty(message = "Senha é obrigatoria")
        String password
) {
}
