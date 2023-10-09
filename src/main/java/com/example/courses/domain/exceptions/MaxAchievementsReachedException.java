package com.example.courses.domain.exceptions;

public class MaxAchievementsReachedException extends RuntimeException {

    public MaxAchievementsReachedException() {

    }

    public MaxAchievementsReachedException(String message) {
        super(message);
    }
}
