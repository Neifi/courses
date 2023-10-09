package com.example.shared.domain;

import com.example.courses.domain.events.EventType;

import java.util.List;

public interface DomainEventBus {
    void publish(List<DomainEvent> domainEvent);
    void subscribe(EventType domainEvent, DomainEventHandler<DomainEvent> handler);
}
