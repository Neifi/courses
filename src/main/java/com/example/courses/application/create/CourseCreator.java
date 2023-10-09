package com.example.courses.application.create;

import com.example.courses.domain.aggregates.Course;
import com.example.courses.domain.entities.Creator;
import com.example.courses.domain.vo.Email;
import com.example.courses.domain.service.CreateCourseService;
import com.example.shared.domain.DomainEventBus;

import java.util.ArrayList;

public class CourseCreator implements CreateCourseService {

    private final DomainEventBus eventBus;

    public CourseCreator(DomainEventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public Course createCourse(String creatorEmail, String name) {


        return null;
    }
}
