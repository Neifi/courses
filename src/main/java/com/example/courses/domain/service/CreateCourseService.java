package com.example.courses.domain.service;

import com.example.courses.domain.aggregates.Course;

public interface CreateCourseService {
    Course createCourse(String creatorEmail,String name);
}

