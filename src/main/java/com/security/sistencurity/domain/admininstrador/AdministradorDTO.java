package com.security.sistencurity.domain.admininstrador;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalTime;

public record AdministradorDTO(
        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @NotBlank
        String telefone,
        @NotBlank
        LocalTime comecoExpediente,
        @NotBlank
        LocalTime fimExpediente

) {
}
