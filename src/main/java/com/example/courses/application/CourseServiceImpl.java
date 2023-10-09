package com.example.courses.application;

import com.example.courses.domain.aggregates.Course;
import com.example.courses.domain.CourseReadModel;
import com.example.courses.domain.PageableCourseReadModel;
import com.example.courses.domain.service.CourseService;
import com.example.courses.domain.service.*;

import java.util.UUID;

public class CourseServiceImpl implements CourseService {

    private final CreateCourseService createCourseService;
    private final DeleteCourseService deleteCourseService;
    private final UpdateCourseService updateCourseService;
    private final GetCourseByIdService getCourseByIdService;
    private final FindAllCoursesService findAllCoursesService;

    public CourseServiceImpl(CreateCourseService createCourseService,
                             DeleteCourseService deleteCourseService,
                             UpdateCourseService updateCourseService, GetCourseByIdService getCourseByIdService, FindAllCoursesService findAllCoursesService) {
        this.createCourseService = createCourseService;
        this.deleteCourseService = deleteCourseService;
        this.updateCourseService = updateCourseService;
        this.getCourseByIdService = getCourseByIdService;
        this.findAllCoursesService = findAllCoursesService;
    }

    @Override
    public Course createCourse(String creatorEmail,String name) {
        return createCourseService.createCourse(creatorEmail,name);
    }

    @Override
    public PageableCourseReadModel findAllCourses(int page, int pSize) {
        return findAllCoursesService.findAllCourses(page,pSize);
    }

    @Override
    public CourseReadModel getCourseById(UUID id) {
        return getCourseByIdService.getCourseById(id);
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
