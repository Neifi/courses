package com.example.shared.domain;

import com.example.courses.domain.events.EventType;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class DomainEvent {

    public abstract EventType type();

    public abstract UUID aggregateId();

    public abstract int version();

    public abstract String serialize();

}
