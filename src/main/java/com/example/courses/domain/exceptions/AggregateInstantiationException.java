package com.example.courses.domain.exceptions;

public class AggregateInstantiationException extends RuntimeException {
    public AggregateInstantiationException() {
    }

    public AggregateInstantiationException(String message) {
        super(message);
    }
}
