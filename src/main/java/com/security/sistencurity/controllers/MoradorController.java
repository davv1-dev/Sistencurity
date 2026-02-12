package com.security.sistencurity.controllers;


import com.security.sistencurity.domain.morador.MoradorDTO;
import com.security.sistencurity.domain.morador.MoradorService;
import com.security.sistencurity.domain.usuario.Perfil;
import com.security.sistencurity.domain.usuario.Usuario;
import com.security.sistencurity.domain.visitante.VisitanteDTO;
import com.security.sistencurity.infra.exceptions.NaoPossuiAltorizacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sistencurity")
public class MoradorController {
    @Autowired
    private MoradorService service;

    @PostMapping("/cadastro-morador")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity cadastroMorador(@RequestBody MoradorDTO moradorNovo){
       var retorno = service.cadastrarMoradorNovo(moradorNovo);
       return ResponseEntity.status(HttpStatus.CREATED).body("Novo morador cadastrado com sucesso:\n"+retorno);
    }
    @PostMapping("/cadastro-visitante")
    @PreAuthorize("hasRole('MORADOR')")
    public ResponseEntity cadastrarVisitante(@RequestBody VisitanteDTO visitante,@AuthenticationPrincipal Usuario logado){
        var retorno = service.adicionarVisitanteALista(visitante, logado.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body("Visitante adicionado a sua lista com sucesso.\n"+retorno);
    }
}
