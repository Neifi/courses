package com.example.shared.domain;

import com.example.courses.domain.events.EventType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InMemoryDomainEventBus implements DomainEventBus {
    private Map<EventType, Set<DomainEventHandler<DomainEvent>>> bus = new HashMap<>();

    public InMemoryDomainEventBus() {
    }

    public void publish(List<DomainEvent> domainEvent) {
        for (DomainEvent event : domainEvent) {
            if (bus.containsKey(event.type())) {
                bus.get(event.type()).forEach(domainEventHandler -> domainEventHandler.handle(event));
            }
        }
    }

    public void subscribe(EventType domainEvent, DomainEventHandler<DomainEvent> handler) {
        if (bus.containsKey(domainEvent)) {
            Set<DomainEventHandler<DomainEvent>> domainEventHandlers = bus.get(domainEvent);
            domainEventHandlers.add(handler);
        } else {
            bus.put(domainEvent, Set.of(handler));
        }
    }

}
