package com.security.sistencurity.infra.exceptions;

public class RefreshTokenExpiradoException extends RuntimeException {
    public RefreshTokenExpiradoException(String message) {
        super(message);
    }
}
