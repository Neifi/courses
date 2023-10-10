package com.example.courses.domain.events;

import com.example.courses.domain.vo.Email;
import com.example.shared.domain.DomainEvent;

import java.util.UUID;

public class CollaboratorInvitedEvent extends DomainEvent {
    private final UUID aggregateID;
    private final Email collaboratorEmail;

    public CollaboratorInvitedEvent(UUID aggregateID, Email collaboratorEmail) {
        this.aggregateID = aggregateID;
        this.collaboratorEmail = collaboratorEmail;
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
