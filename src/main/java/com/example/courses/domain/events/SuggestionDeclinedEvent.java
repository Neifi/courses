package com.example.courses.domain.events;

import com.example.courses.domain.events.EventType;
import com.example.shared.domain.DomainEvent;

import java.util.UUID;

public class SuggestionDeclinedEvent extends DomainEvent {
    private final UUID aggregateID;
    private final UUID suggestionID;
    public SuggestionDeclinedEvent(UUID suggestionID, UUID aggregateID) {
        super();
        this.aggregateID = aggregateID;
        this.suggestionID = suggestionID;
    }

    @Override
    public EventType type() {
        return null;
    }

    @Override
    public UUID aggregateId() {
        return this.aggregateID;
    }

    @Override
    public int version() {
        return 0;
    }

    @Override
    public String serialize() {
        return null;
    }
}
