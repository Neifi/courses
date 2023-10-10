package com.example.courses.domain.exceptions;

public class CourseModuleNotFoundException extends RuntimeException {
    public CourseModuleNotFoundException() {
    }

    public CourseModuleNotFoundException(String message) {
        super(message);
    }
}
