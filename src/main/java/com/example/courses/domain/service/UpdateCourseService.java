package com.example.courses.domain.service;

import java.util.UUID;

public interface UpdateCourseService {
    void updateCourse(UUID id, String courseName);
}
