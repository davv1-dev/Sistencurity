package com.security.sistencurity.domain.visitante;

import jakarta.validation.constraints.NotBlank;

public record VisitanteDTO(
        @NotBlank
        String nome,
        @NotBlank
        String cpf
) {
}
