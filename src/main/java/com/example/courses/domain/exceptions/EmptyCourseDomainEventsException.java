package com.example.courses.domain.exceptions;

public class EmptyCourseDomainEventsException extends RuntimeException{
    public EmptyCourseDomainEventsException() {
    }

    public EmptyCourseDomainEventsException(String message) {
        super(message);
    }
}
