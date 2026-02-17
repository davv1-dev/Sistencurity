package com.security.sistencurity.domain.visitante;

import com.security.sistencurity.domain.morador.Morador;
import com.security.sistencurity.domain.morador.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

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

    public Page<ListaVisitantesDTO> listarVisitantePorMorador(Long idmorador, Pageable page){
        return repository.findByMorador_Id(idmorador,page).map(ListaVisitantesDTO::new);
    }
}
