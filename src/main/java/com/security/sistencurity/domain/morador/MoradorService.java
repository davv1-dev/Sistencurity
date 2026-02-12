package com.security.sistencurity.domain.morador;

import com.security.sistencurity.domain.usuario.Perfil;
import com.security.sistencurity.domain.usuario.UsuarioService;
import com.security.sistencurity.domain.visitante.VisitanteDTO;
import com.security.sistencurity.domain.visitante.VisitanteDTORetorno;
import com.security.sistencurity.domain.visitante.VisitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoradorService {
    @Autowired
    private MoradorRepository repository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private VisitanteService visitanteService;

    public MoradorDTORetorno cadastrarMoradorNovo(MoradorDTO moradorNovo) {
        Long id = usuarioService.cadastrarUsuario(moradorNovo.nomeCompleto(),moradorNovo.cpf(), Perfil.MORADOR);
        Morador morador = new Morador(id,moradorNovo);
        repository.save(morador);
        MoradorDTORetorno retorno = new MoradorDTORetorno(morador);
        return retorno;
    }
    public VisitanteDTO adicionarVisitanteALista(VisitanteDTO visitante,Long id){
        var morador = repository.getReferenceById(id);
        var visitanteNovo = visitanteService.cadastraVisitante(visitante,morador);
        morador.adicionarVisitante(visitanteNovo);
        return visitante;
    }
}
