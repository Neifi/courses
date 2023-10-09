package com.example.shared.infrastructure;

import com.example.courses.domain.events.EventType;
import com.example.shared.domain.DomainEvent;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class V1CourseUpdatedInfrastructureEvent implements InfrastructureEvent {
    private final UUID id;
    private final String data;
    private final String eventType;
    private final Instant timestamp;
    private final int version;

    public V1CourseUpdatedInfrastructureEvent(DomainEvent domainEvent) {
        this.id = UUID.randomUUID();
        this.data = domainEvent.serialize();
        this.eventType = EventType.COURSE_CREATED.toString();
        this.timestamp = Instant.now();
        this.version = 1;
    }

    @Override
    public String id() {
        return id.toString();
    }

    @Override
    public String type() {
        return eventType;
    }

    @Override
    public Instant timestamp() {
        return this.timestamp;
    }

    @Override
    public int version() {
        return this.version;
    }

    @Override
    public String payload() {
        return data;
    }

    @Override
    public Map<String, String> metadata() {
        return new HashMap<>();
    }
}
