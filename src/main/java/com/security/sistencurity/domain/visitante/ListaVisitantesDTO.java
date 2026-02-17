package com.security.sistencurity.domain.visitante;

import java.time.Instant;

public record ListaVisitantesDTO(String nome, Instant dataVisita) {
    public ListaVisitantesDTO(Visitante visitante){
        this(visitante.getNomeCompletoVisitante(),visitante.getDataVisita());
    }
}
