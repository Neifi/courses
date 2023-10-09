package com.example.courses.domain;

import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

public record PageableCourseReadModel(
        List<CourseReadModel> courseReadModels,
        long totalElements,
        long totalPages,
        long offset,
        int page,
        long pageSize
) {
}

