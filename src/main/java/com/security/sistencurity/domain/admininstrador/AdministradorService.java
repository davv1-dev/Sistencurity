package com.security.sistencurity.domain.admininstrador;

import com.security.sistencurity.domain.usuario.Perfil;
import com.security.sistencurity.domain.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository repository;
    @Autowired
    private UsuarioService usuarioService;

    public AdministradorDTORetorno salvarAdm(@Valid AdministradorDTO dados) {
        Long id = usuarioService.cadastrarUsuario(dados.nome(), dados.cpf(), Perfil.ADMINISTRADOR);
        Administrador administrador = new Administrador(id,dados);
        repository.save(administrador);
        return new AdministradorDTORetorno(administrador.getNomeCompleto());
    }
}
