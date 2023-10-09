package com.example.shared.domain.exceptions;

public class RetryException extends RuntimeException {
    public RetryException(String msg) {
        super(msg);
    }
}
