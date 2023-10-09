package com.example.courses.application.update;

import com.example.courses.application.exception.CourseNotFoundException;
import com.example.courses.domain.aggregates.Course;
import com.example.courses.domain.service.UpdateCourseService;
import com.example.courses.infrastructure.persistence.sql.DomainEventEntity;
import com.example.courses.infrastructure.persistence.sql.DomainEventFactory;
import com.example.shared.domain.JsonSerDe;
import com.example.courses.infrastructure.persistence.sql.JpaDomainEventRepository;
import com.example.shared.domain.DomainEvent;
import com.example.shared.domain.DomainEventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CourseUpdater implements UpdateCourseService {

    private final DomainEventBus eventBus;
    private final JpaDomainEventRepository domainEventRepository;
    private final JsonSerDe<DomainEvent> jsonSerDe;

    public CourseUpdater(DomainEventBus eventBus, JpaDomainEventRepository domainEventRepository,
                         JsonSerDe<DomainEvent> jsonSerDe) {
        this.eventBus = eventBus;
        this.domainEventRepository = domainEventRepository;
        this.jsonSerDe = jsonSerDe;
    }

    @Override
    public void updateCourse(UUID id, String courseName) {

        List<DomainEventEntity> allByAggregateId = this.domainEventRepository
                .findAllByAggregateId(id.toString());
        if (allByAggregateId.isEmpty()) {
            throw new CourseNotFoundException("course not found with id: " + id.toString());
        }

        List<DomainEvent> domainEvents = DomainEventFactory.buildDomaintEvents(allByAggregateId);

        Course course = Course.rehydrateFromEvents(domainEvents);
        course.updateName(courseName);

        eventBus.publish(new ArrayList<>(course.pullDomainEvents()));
    }

}

