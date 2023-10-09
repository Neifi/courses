package com.example.courses.domain.exceptions;

public class SuggestionNotFoundException extends RuntimeException{
    public SuggestionNotFoundException() {
        super("suggestion not found");
    }

    public SuggestionNotFoundException(String message) {
        super(message);
    }
}
