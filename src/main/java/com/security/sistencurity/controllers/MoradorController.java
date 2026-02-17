package com.security.sistencurity.controllers;


import com.security.sistencurity.domain.morador.MoradorDTO;
import com.security.sistencurity.domain.morador.MoradorService;
import com.security.sistencurity.domain.usuario.Usuario;
import com.security.sistencurity.domain.visitante.ListaVisitantesDTO;
import com.security.sistencurity.domain.visitante.VisitanteDTO;
import com.security.sistencurity.domain.visitante.VisitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/sistencurity")
public class MoradorController {
    @Autowired
    private MoradorService service;
    @Autowired
    private VisitanteService visitanteService;

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
    @GetMapping("/meus-visitantes")
    @PreAuthorize("hasRole('MORADOR')")
    public ResponseEntity mostrarVisitantes(@AuthenticationPrincipal Usuario logado,Pageable page){
        Page<ListaVisitantesDTO> pageRetorno = visitanteService.listarVisitantePorMorador(logado.getId(),page);
        return ResponseEntity.status(HttpStatus.OK).body("Estes são seus visitantes:\n"+pageRetorno);
    }
}
