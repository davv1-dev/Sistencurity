package com.security.sistencurity.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
        @NotNull
        String nome,
        @NotNull
        String senha,
        @NotNull
        Perfil perfil) {
}
