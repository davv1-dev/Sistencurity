package com.security.sistencurity.controllers;

import com.security.sistencurity.domain.auth.RefreshDTO;
import com.security.sistencurity.domain.usuario.UsuarioDTOEntrada;
import com.security.sistencurity.domain.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sistencurity/login")
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UsuarioDTOEntrada usuario){
        var token = new UsernamePasswordAuthenticationToken(usuario.nome(),usuario.senha());
        var authentication = manager.authenticate(token);
        var retornoTokens = service.efetuarLogin(usuario,authentication);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(retornoTokens);
    }
    @PostMapping("/refresh")
    public ResponseEntity refresh(@RequestBody @Valid RefreshDTO refreshDTO){
        var retornoTokens = service.gerarNovoToken(refreshDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(retornoTokens);
    }
    @PostMapping("/logout")
    public ResponseEntity logout(Authentication authentication){
        service.fazerLogout(authentication);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
