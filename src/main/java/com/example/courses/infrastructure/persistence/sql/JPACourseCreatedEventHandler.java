package com.example.courses.infrastructure.persistence.sql;

import com.example.courses.domain.events.CourseCreatedEvent;
import com.example.courses.domain.events.EventType;
import com.example.shared.domain.*;
import com.example.shared.infrastructure.V1CourseCreatedInfrastructureEvent;
import com.example.shared.infrastructure.InfrastructureEventBus;

import java.time.Instant;

public class JPACourseCreatedEventHandler implements DomainEventHandler<DomainEvent> {
    private final JpaDomainEventRepository jpaDomainEventRepository;
    private final DomainEventBus eventBus;

    private final InfrastructureEventBus infrastructureEventBus;
    private final JsonSerDe<CourseCreatedEvent> jsonSerDe;

    public JPACourseCreatedEventHandler(JpaDomainEventRepository jpaDomainEventRepository,
                                        DomainEventBus eventBus,
                                        InfrastructureEventBus infrastructureEventBus,
                                        JsonSerDe<CourseCreatedEvent> jsonSerDe) {
        this.jpaDomainEventRepository = jpaDomainEventRepository;
        this.eventBus = eventBus;
        this.infrastructureEventBus = infrastructureEventBus;
        this.jsonSerDe = jsonSerDe;
        this.eventBus.subscribe(EventType.COURSE_CREATED, this);
    }

    private void saveDomainEvent(DomainEvent domainEvent) {

        String eventData = domainEvent.serialize();
        DomainEventEntity domainEventEntity = new DomainEventEntity(
                domainEvent.aggregateId().toString(),
                domainEvent.type().toString(),
                eventData,
                Instant.now(),
                domainEvent.version());

        jpaDomainEventRepository.saveDomainEvent(domainEventEntity);

        infrastructureEventBus.publish(new V1CourseCreatedInfrastructureEvent(domainEvent));
    }

    @Override
    public void handle(DomainEvent event) {
        saveDomainEvent( event);
    }
}

