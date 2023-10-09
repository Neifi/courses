package com.example.courses.application.remove;

import com.example.courses.domain.aggregates.Course;
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

    private final DomainEventBus eventBus;
    private final JpaDomainEventRepository domainEventRepository;
    private final JsonSerDe<DomainEvent> jsonSerDe;

    public CourseRemover(DomainEventBus eventBus, JpaDomainEventRepository domainEventRepository, JsonSerDe<DomainEvent> jsonSerDe) {
        this.eventBus = eventBus;
        this.domainEventRepository = domainEventRepository;
        this.jsonSerDe = jsonSerDe;
    }

    @Override
    public void deleteCourse(UUID id) {

        List<DomainEventEntity> domainEventEntities = this.domainEventRepository
                .findAllByAggregateId(id.toString());

        List<DomainEvent> domainEvents = DomainEventFactory.buildDomaintEvents(domainEventEntities);

        Course course = Course.rehydrateFromEvents(domainEvents);
        course.delete();

        eventBus.publish(new ArrayList<>(course.pullDomainEvents()));
    }

}
