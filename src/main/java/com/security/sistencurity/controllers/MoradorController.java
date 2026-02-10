package com.security.sistencurity.controllers;

import com.security.sistencurity.domain.morador.Morador;
import com.security.sistencurity.domain.morador.MoradorDTO;
import com.security.sistencurity.domain.morador.MoradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sistencurity")
public class MoradorController {
    @Autowired
    private MoradorService service;

    @PostMapping("/cadastromorador")
    public ResponseEntity cadastroMorador(@RequestBody MoradorDTO moradorNovo){
       var retorno = service.cadastrarMoradorNovo(moradorNovo);
       return ResponseEntity.status(HttpStatus.CREATED).body("Novo morador cadastrado com sucesso:\n"+retorno);
    }
}
