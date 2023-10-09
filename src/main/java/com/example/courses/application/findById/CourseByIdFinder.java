package com.example.courses.application.findById;

import com.example.courses.domain.CourseReadModel;
import com.example.courses.domain.CourseReadRepository;
import com.example.courses.domain.service.GetCourseByIdService;

import java.util.UUID;

public class CourseByIdFinder implements GetCourseByIdService {
    private final CourseReadRepository courseReadRepository;
    public CourseByIdFinder(CourseReadRepository courseReadRepository) {
        this.courseReadRepository = courseReadRepository;
    }

    @Override
    public CourseReadModel getCourseById(UUID id) {
        return courseReadRepository.getCourseById(id);
    }
}
