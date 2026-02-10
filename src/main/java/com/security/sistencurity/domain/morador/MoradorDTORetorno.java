package com.security.sistencurity.domain.morador;

import jakarta.validation.constraints.NotNull;

public record MoradorDTORetorno(
        @NotNull
        String nome,
        @NotNull
        String torre
) {
    public MoradorDTORetorno(Morador morador){
        this(morador.getNomeCompleto(),morador.getTorre());
    }
}
