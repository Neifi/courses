package com.example.courses.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public record CourseJsonResponse(
        @JsonProperty("courseId")
        String courseId,
        @JsonProperty("name")
        String name
) {
}


