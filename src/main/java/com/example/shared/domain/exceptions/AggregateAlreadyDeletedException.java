package com.example.shared.domain.exceptions;

public class AggregateAlreadyDeletedException extends RuntimeException{
    public AggregateAlreadyDeletedException() {
        super("aggregate al ready deleted");
    }

    public AggregateAlreadyDeletedException(String message) {
        super(message);
    }
}
