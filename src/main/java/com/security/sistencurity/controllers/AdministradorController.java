package com.security.sistencurity.controllers;

import com.security.sistencurity.domain.admininstrador.AdministradorDTO;
import com.security.sistencurity.domain.admininstrador.AdministradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sistencurity")
public class AdministradorController {
    @Autowired
    private AdministradorService service;

    @PostMapping("/cadastrar-adm")
    public ResponseEntity cadastrarAdiministrador(@RequestBody @Valid AdministradorDTO dados){
        var retorno = service.salvarAdm(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body("Bem-vindo "+retorno);
    }
}
