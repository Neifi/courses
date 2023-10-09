package com.example.courses.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public record CourseJsonRequest(
        String creatorEmail,
        String name

) {
}