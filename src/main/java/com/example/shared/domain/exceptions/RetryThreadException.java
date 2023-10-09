package com.example.shared.domain.exceptions;

public class RetryThreadException extends RuntimeException {
    public RetryThreadException(String msg) {
        super(msg);
    }
}
