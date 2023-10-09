package com.example.courses.application;

import com.example.courses.domain.aggregates.Course;
import com.example.courses.domain.service.CourseService;
import com.example.courses.domain.service.*;

import java.util.UUID;

public class CourseServiceImpl implements CourseService {

    private final CreateCourseService createCourseService;
    private final DeleteCourseService deleteCourseService;
    private final UpdateCourseService updateCourseService;

    public CourseServiceImpl(CreateCourseService createCourseService,
                             DeleteCourseService deleteCourseService,
                             UpdateCourseService updateCourseService) {
        this.createCourseService = createCourseService;
        this.deleteCourseService = deleteCourseService;
        this.updateCourseService = updateCourseService;
    }

    @Override
    public Course createCourse(String creatorEmail,String name) {
        return createCourseService.createCourse(creatorEmail,name);
    }

    @Override
    public void updateCourse(UUID id, String courseName) {
        updateCourseService.updateCourse(id, courseName);
    }

    @Override
    public void deleteCourse(UUID id) {
        deleteCourseService.deleteCourse(id);
    }

}
