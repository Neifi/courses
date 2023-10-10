package com.example.courses.infrastructure.rest.dto;

import com.example.courses.domain.entities.CourseModule;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;

public record CourseJsonRequest(
        String creatorEmail,
        String name,
        List<CourseModule> modules

) {
}