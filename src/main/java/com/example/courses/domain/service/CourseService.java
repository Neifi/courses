package com.example.courses.domain.service;

import com.example.courses.domain.aggregates.Course;
import com.example.courses.domain.entities.CourseModule;

import java.util.List;
import java.util.UUID;


public interface CourseService {
    Course createCourse(String creatorEmail, String name, List<CourseModule> modules);

    void updateCourse(UUID id, String courseName);

    void deleteCourse(UUID id);

    void inviteCollaborator(UUID courseID, String collaboratorEmail);

}
