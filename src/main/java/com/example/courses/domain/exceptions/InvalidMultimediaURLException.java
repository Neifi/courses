package com.example.courses.domain.exceptions;

public class InvalidMultimediaURLException extends RuntimeException {
    public InvalidMultimediaURLException() {
        super("invalid url");
    }

    public InvalidMultimediaURLException(String message) {
        super(message);
    }
}
