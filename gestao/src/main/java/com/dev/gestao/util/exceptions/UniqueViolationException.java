package com.dev.gestao.util.exceptions;

public class UniqueViolationException extends RuntimeException{
    public UniqueViolationException() {
        super();
    }

    public UniqueViolationException(final String message) {
        super(message);
    }
}
