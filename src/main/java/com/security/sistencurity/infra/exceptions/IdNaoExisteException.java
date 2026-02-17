package com.security.sistencurity.infra.exceptions;

public class IdNaoExisteException extends RuntimeException {
    public IdNaoExisteException(String message) {
        super(message);
    }
}
