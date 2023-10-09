package com.example.courses.infrastructure.persistence.sql;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "domain_event")
public class DomainEventEntity {

    @Id
    @Column(name = "event_id")
    @UuidGenerator
    private String eventId;

    @Column(name = "aggregate_id", nullable = false)
    private String aggregateId;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Column(name = "event_data", columnDefinition = "JSON", nullable = false)
    private String eventData;

    @Column(name = "occurred_at", nullable = false)
    private Instant occurredAt;

    @Column(name = "version", nullable = false)
    private int version ;


    public DomainEventEntity( String aggregateId, String eventType, String eventData, Instant occurredAt, int version) {
        this.version = version;
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.eventData = eventData;
        this.occurredAt = occurredAt;
    }

    public DomainEventEntity() {
    }

    public String getEventId() {
        return eventId;
    }

    public String getAggregateId() {
        return aggregateId;
    }


    public String getEventType() {
        return eventType;
    }

    public String getEventData() {
        return eventData;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    public int getVersion() {
        return version;
    }


    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    public void setOccurredAt(Instant occurredAt) {
        this.occurredAt = occurredAt;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
