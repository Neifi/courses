package com.example.courses.application.remove;

import com.example.courses.domain.aggregates.Course;
import com.example.courses.domain.repositories.CourseRepository;
import com.example.courses.domain.service.DeleteCourseService;
import com.example.courses.infrastructure.persistence.sql.DomainEventEntity;
import com.example.courses.infrastructure.persistence.sql.DomainEventFactory;
import com.example.shared.domain.JsonSerDe;
import com.example.courses.infrastructure.persistence.sql.JpaDomainEventRepository;
import com.example.shared.domain.DomainEvent;
import com.example.shared.domain.DomainEventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CourseRemover implements DeleteCourseService {

    private final JpaDomainEventRepository domainEventRepository;
    private final JsonSerDe<DomainEvent> jsonSerDe;
    private final CourseRepository courseRepository;

    public CourseRemover(JpaDomainEventRepository domainEventRepository, JsonSerDe<DomainEvent> jsonSerDe, CourseRepository courseRepository) {
        this.domainEventRepository = domainEventRepository;
        this.jsonSerDe = jsonSerDe;
        this.courseRepository = courseRepository;
    }

    @Override
    public void deleteCourse(UUID id) {
        Course course = courseRepository.findById(id);
        course.delete();

        this.courseRepository.deleteCourse(course);
    }

}
