package com.security.sistencurity.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record AlteracaoDeSenhaDTO(@NotBlank String senhaAtual, @NotBlank String novaSenha, @NotBlank String novaSenhaConfirmacao) {
}
