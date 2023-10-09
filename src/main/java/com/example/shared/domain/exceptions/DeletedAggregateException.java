package com.example.shared.domain.exceptions;

public class DeletedAggregateException extends RuntimeException {
    public DeletedAggregateException(String msg) {
        super(msg);
    }

    public DeletedAggregateException() {
        super("operation not supported on deleted aggregates");
    }
}
