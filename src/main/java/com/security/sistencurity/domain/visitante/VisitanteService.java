package com.security.sistencurity.domain.visitante;

import com.security.sistencurity.domain.morador.Morador;
import com.security.sistencurity.domain.morador.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitanteService {
    @Autowired
    private VisitanteRepository repository;
    @Autowired
    private MoradorRepository moradorRepository;

    public Visitante cadastraVisitante(VisitanteDTO dados, Morador morador){
        Visitante visitante = new Visitante(dados,morador);
        repository.save(visitante);
        return visitante;
    }
}
