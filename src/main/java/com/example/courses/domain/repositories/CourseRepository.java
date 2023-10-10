package com.example.courses.domain.repositories;

import com.example.courses.domain.aggregates.Course;

import java.util.UUID;

public interface CourseRepository {
    void createCourse(Course course);

    void updateCourse(Course course);

    void deleteCourse(Course course);

    Course findById(UUID id);
}
