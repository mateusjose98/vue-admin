package com.dev.gestao.util;

public class UniqueViolationException extends RuntimeException{
    public UniqueViolationException() {
        super();
    }

    public UniqueViolationException(final String message) {
        super(message);
    }
}
