package com.example.courses.domain.service;

import com.example.courses.domain.CourseReadModel;

import java.util.UUID;

public interface GetCourseByIdService {
    CourseReadModel getCourseById(UUID id);
}
