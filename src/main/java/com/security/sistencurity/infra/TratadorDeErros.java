package com.security.sistencurity.infra;

import com.security.sistencurity.domain.auth.RefreshToken;
import com.security.sistencurity.infra.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(ErroAoGerarTokenException.class)
    public ResponseEntity<List<DadosErro>> erroAoGerarTokenJWT(ErroAoGerarTokenException e){
        DadosErro erro = new DadosErro("TokenJWT",e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(erro));
    }
    @ExceptionHandler(NaoPossuiAutorizacaoException.class)
    public ResponseEntity<List<DadosErro>> naoPossuiAutorizacao(NaoPossuiAutorizacaoException e){
        DadosErro erro = new DadosErro("Não autorizado",e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(List.of(erro));
    }
    @ExceptionHandler(RefreshTokenExpiradoException.class)
    public ResponseEntity<List<DadosErro>> refreshTokenExpirado(RefreshTokenExpiradoException e){
        DadosErro erro = new DadosErro("RefreshToken",e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(List.of(erro));
    }
    @ExceptionHandler(RefreshTokenNaoExisteException.class)
    public ResponseEntity<List<DadosErro>> refreshTokenNaoExiste(RefreshTokenNaoExisteException e){
        DadosErro erro = new DadosErro("RefreshToken",e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(erro));
    }
    @ExceptionHandler(TokenInvalidoException.class)
    public ResponseEntity<List<DadosErro>> tokenInvalido(TokenInvalidoException e){
        DadosErro erro = new DadosErro("TokenJWT",e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(List.of(erro));
    }
    @ExceptionHandler(IdNaoExisteException.class)
    public ResponseEntity<List<DadosErro>> idNaoExsite(IdNaoExisteException e){
        DadosErro erro = new DadosErro("Id",e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(erro));
    }

    private record DadosErro(String campo,String mensagem){
        public DadosErro(FieldError e){
            this(e.getField(),e.getDefaultMessage());
        }
    }
}
