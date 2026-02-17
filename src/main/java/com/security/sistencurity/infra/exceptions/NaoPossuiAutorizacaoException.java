package com.security.sistencurity.infra.exceptions;

public class NaoPossuiAutorizacaoException extends RuntimeException {
    public NaoPossuiAutorizacaoException(String message) {
        super(message);
    }
}
