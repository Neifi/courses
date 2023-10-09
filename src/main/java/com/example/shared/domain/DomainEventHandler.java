package com.example.shared.domain;

public interface DomainEventHandler<T extends DomainEvent> {
    void handle(T event);
}
