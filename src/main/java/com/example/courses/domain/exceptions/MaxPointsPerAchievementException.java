package com.example.courses.domain.exceptions;

public class MaxPointsPerAchievementException extends RuntimeException {
    public MaxPointsPerAchievementException() {
    }

    public MaxPointsPerAchievementException(String message) {
        super(message);
    }
}
