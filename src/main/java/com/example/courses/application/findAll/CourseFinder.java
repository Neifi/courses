package com.example.courses.application.findAll;

import com.example.courses.domain.CourseReadRepository;
import com.example.courses.domain.PageableCourseReadModel;
import com.example.courses.domain.service.FindAllCoursesService;

public class CourseFinder implements FindAllCoursesService {
    private final CourseReadRepository courseReadRepository;


    public CourseFinder(CourseReadRepository courseReadRepository) {
        this.courseReadRepository = courseReadRepository;
    }


    @Override
    public PageableCourseReadModel findAllCourses(int page, int pSize) {
        PageableCourseReadModel allCourses = courseReadRepository.findAllCourses(page, pSize);
        return allCourses;
    }
}
