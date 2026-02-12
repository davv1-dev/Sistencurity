package com.security.sistencurity.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTOEntrada(
        @NotBlank
        String nome,
        @NotBlank
        String senha
) {
}
