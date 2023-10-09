package com.example.courses.domain.service;

import com.example.courses.domain.aggregates.Course;

import java.util.UUID;


public interface CourseService {
    Course createCourse(String creatorEmail,String name);

    void updateCourse(UUID id, String courseName);

    void deleteCourse(UUID id);

}
