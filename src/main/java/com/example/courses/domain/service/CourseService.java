package com.example.courses.domain.service;

import com.example.courses.domain.aggregates.Course;
import com.example.courses.domain.CourseReadModel;
import com.example.courses.domain.PageableCourseReadModel;

import java.util.UUID;


public interface CourseService {
    Course createCourse(String creatorEmail,String name);

    PageableCourseReadModel findAllCourses(int page, int pSize);

    CourseReadModel getCourseById(UUID id);

    void updateCourse(UUID id, String courseName);

    void deleteCourse(UUID id);


}
