package com.security.sistencurity.domain.morador;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record MoradorDTO(
        @NotBlank
         String nomeCompleto,
         @CPF
         String cpf,
         @NotBlank
         String numeroTelefone,
         @NotBlank
         String torre,
         @NotBlank
         String apartamento) {
}
