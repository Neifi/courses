package com.example.courses.domain.events;

import com.example.courses.domain.events.EventType;
import com.example.shared.domain.DomainEvent;

import java.util.UUID;

public class SuggestionApprovedEvent extends DomainEvent {
    private final UUID aggregateID;
    private final UUID suggestionID;

    public SuggestionApprovedEvent(UUID suggestionID, UUID aggregateID) {
        super();
        this.suggestionID = suggestionID;
        this.aggregateID = aggregateID;
    }

    @Override
    public EventType type() {
        return null;
    }

    @Override
    public UUID aggregateId() {
        return aggregateID;
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
