package com.example.courses.infrastructure.persistence.sql;

import com.example.courses.domain.events.CourseDeletedEvent;
import com.example.courses.domain.events.EventType;
import com.example.shared.domain.JsonSerDe;
import com.example.shared.domain.DomainEvent;
import com.example.shared.domain.DomainEventBus;
import com.example.shared.domain.DomainEventHandler;
import com.example.shared.infrastructure.InfrastructureEventBus;
import com.example.shared.infrastructure.V1CourseUpdatedInfrastructureEvent;

import java.time.Instant;

public class JPACourseDeletedEventHandler implements DomainEventHandler<DomainEvent> {
    private final JpaDomainEventRepository jpaDomainEventRepository;
    private final DomainEventBus eventBus;
    private final InfrastructureEventBus infrastructureEventBus;

    private final JsonSerDe<CourseDeletedEvent> jsonSerDe;
    public JPACourseDeletedEventHandler(JpaDomainEventRepository jpaDomainEventRepository, DomainEventBus eventBus, InfrastructureEventBus infrastructureEventBus, JsonSerDe<CourseDeletedEvent> jsonSerDe) {
        this.jpaDomainEventRepository = jpaDomainEventRepository;
        this.eventBus = eventBus;
        this.infrastructureEventBus = infrastructureEventBus;
        this.jsonSerDe = jsonSerDe;
        this.eventBus.subscribe(EventType.COURSE_DELETED, this);
    }

    private void saveDomainEvent(DomainEvent domainEvent) {
        DomainEventEntity domainEventEntity = new DomainEventEntity(
                domainEvent.aggregateId().toString(),
                domainEvent.type().toString(),
                domainEvent.serialize(),
                Instant.now(),
                domainEvent.version());

        jpaDomainEventRepository.saveDomainEvent(domainEventEntity);

        this.infrastructureEventBus.publish(new V1CourseUpdatedInfrastructureEvent(domainEvent));

    }

    @Override
    public void handle(DomainEvent event) {
        saveDomainEvent(event);
    }
}

