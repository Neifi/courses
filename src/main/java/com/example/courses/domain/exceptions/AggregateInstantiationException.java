package com.example.courses.domain.exceptions;

public class AggregateInstantiationException extends IllegalArgumentException {
    public AggregateInstantiationException() {
    }

    public AggregateInstantiationException(String message) {
        super(message);
    }
}
