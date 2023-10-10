package com.example.shared.domain;

import org.springframework.util.Assert;

import java.util.*;

public abstract class AbstractAggregate<A extends AbstractAggregate<A>> {
    protected String id;
    protected int version = 0;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    protected void registerEvent(DomainEvent event) {

        Assert.notNull(event, "domain event must not be null");

        this.domainEvents.add(event);
    }

    protected void clearDomainEvents() {
        this.domainEvents.clear();
    }

    protected abstract A apply(List<DomainEvent> domainEvent);

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return events;
    }


}