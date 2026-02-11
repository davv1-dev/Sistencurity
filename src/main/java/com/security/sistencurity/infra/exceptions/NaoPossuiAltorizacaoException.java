package com.security.sistencurity.infra.exceptions;

public class NaoPossuiAltorizacaoException extends RuntimeException {
    public NaoPossuiAltorizacaoException(String message) {
        super(message);
    }
}
