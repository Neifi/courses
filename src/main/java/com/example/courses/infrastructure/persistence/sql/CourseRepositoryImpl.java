package com.example.courses.infrastructure.persistence.sql;

import com.example.courses.domain.aggregates.Course;
import com.example.courses.domain.repositories.CourseRepository;
import com.example.shared.domain.DomainEvent;
import com.example.shared.domain.DomainEventBus;

import java.util.List;
import java.util.UUID;

public class CourseRepositoryImpl implements CourseRepository {
    private final DomainEventBus eventBus;
    private final JpaDomainEventRepository jpaDomainEventRepository;

    public CourseRepositoryImpl(DomainEventBus eventBus, JpaDomainEventRepository jpaDomainEventRepository) {
        this.eventBus = eventBus;
        this.jpaDomainEventRepository = jpaDomainEventRepository;
    }

    @Override
    public void createCourse(Course course) {
        eventBus.publish(course.pullDomainEvents());
    }

    @Override
    public void updateCourse(Course course) {
        eventBus.publish(course.pullDomainEvents());
    }

    @Override
    public void deleteCourse(Course course) {
        eventBus.publish(course.pullDomainEvents());
    }

    @Override
    public Course findById(UUID id) {
        List<DomainEventEntity> domainEventEntities = this.jpaDomainEventRepository
                .findAllByAggregateId(id.toString());

        List<DomainEvent> domainEvents = DomainEventFactory.buildDomaintEvents(domainEventEntities);

        return Course.rehydrateFromEvents(domainEvents);
    }
}
