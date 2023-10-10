package com.example.courses.application.create;

import com.example.courses.domain.aggregates.Course;
import com.example.courses.domain.entities.CourseModule;
import com.example.courses.domain.entities.Creator;
import com.example.courses.domain.repositories.CourseRepository;
import com.example.courses.domain.vo.Email;
import com.example.courses.domain.service.CreateCourseService;
import com.example.shared.domain.DomainEventBus;

import java.util.List;

public class CourseCreator implements CreateCourseService {

    private final CourseRepository courseRepository;

    public CourseCreator(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(String creatorEmail,
                               String name,
                               List<CourseModule> modules
    ) {
        Course course = Course.create(Creator.with(new Email(creatorEmail)), name, modules);
        courseRepository.createCourse(course);

        return course;
    }
}
