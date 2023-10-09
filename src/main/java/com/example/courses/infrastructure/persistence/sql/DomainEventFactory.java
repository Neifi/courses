package com.example.courses.infrastructure.persistence.sql;

import com.example.courses.domain.events.CourseCreatedEvent;
import com.example.courses.domain.events.CourseDeletedEvent;
import com.example.courses.domain.events.CourseUpdatedEvent;
import com.example.shared.domain.DomainEvent;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DomainEventFactory {
    public static List<DomainEvent> buildDomaintEvents(List<DomainEventEntity> allByAggregateId) {
        Gson gson = new Gson();
        List<DomainEvent> domainEvents = new ArrayList<>();

        for (DomainEventEntity domainEventEntity : allByAggregateId) {
            switch (domainEventEntity.getEventType()) {
                case "COURSE_CREATED":
                    domainEvents.add(gson.fromJson(domainEventEntity.getEventData(), CourseCreatedEvent.class));
                    break;
                case "COURSE_UPDATED":
                    domainEvents.add(gson.fromJson(domainEventEntity.getEventData(), CourseUpdatedEvent.class));
                    break;
                case "COURSE_DELETED":
                    domainEvents.add(gson.fromJson(domainEventEntity.getEventData(), CourseDeletedEvent.class));
                    break;
                default:
                    throw new IllegalArgumentException("unknown event type: " + domainEventEntity.getEventType());
            }

        }
        return domainEvents;
    }
}
