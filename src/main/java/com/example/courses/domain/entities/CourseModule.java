package com.example.courses.domain.entities;

import com.example.courses.domain.vo.ModuleContent;

import java.time.Duration;
import java.util.UUID;

public record CourseModule(UUID moduleID,String name, Duration duration, ModuleContent moduleContent) {
    public CourseModule {
        if (duration.isNegative() || duration.isZero()) {
            throw new IllegalArgumentException("duration must be positive and greater than zero");

        }
        boolean isGreaterThanTwoHours = duration.toMinutes() > 2 * 60;
        if (isGreaterThanTwoHours) {
            throw new IllegalArgumentException("max duration is 2 hours");
        }
    }
}
