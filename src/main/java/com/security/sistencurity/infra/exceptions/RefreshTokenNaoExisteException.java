package com.security.sistencurity.infra.exceptions;

public class RefreshTokenNaoExisteException extends RuntimeException {
    public RefreshTokenNaoExisteException(String message) {
        super(message);
    }
}
