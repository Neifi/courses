package com.example.courses.domain.exceptions;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException() {
        super("invalid email");
    }

    public InvalidEmailException(String message) {
        super(message);
    }
}
