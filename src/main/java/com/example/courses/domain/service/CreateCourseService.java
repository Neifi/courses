package com.example.courses.domain.service;

import com.example.courses.domain.aggregates.Course;
import com.example.courses.domain.entities.CourseModule;

import java.util.List;

public interface CreateCourseService {
    Course createCourse(String creatorEmail, String name, List<CourseModule> modules);
}

