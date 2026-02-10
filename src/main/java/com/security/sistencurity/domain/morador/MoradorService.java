package com.security.sistencurity.domain.morador;

import com.security.sistencurity.domain.usuario.Perfil;
import com.security.sistencurity.domain.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoradorService {
    @Autowired
    private MoradorRepository repository;
    @Autowired
    private UsuarioService usuarioService;

    public MoradorDTORetorno cadastrarMoradorNovo(MoradorDTO moradorNovo) {
        Long id = usuarioService.cadastrarUsuarioMorador(moradorNovo.nomeCompleto(),moradorNovo.cpf(), Perfil.MORADOR);
        Morador morador = new Morador(id,moradorNovo);
        repository.save(morador);
        MoradorDTORetorno retorno = new MoradorDTORetorno(morador);
        return retorno;
    }

}
