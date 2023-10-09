package com.example.courses.domain.events;

import com.example.shared.domain.DomainEvent;

import java.util.UUID;

public class CourseDeletedEvent extends DomainEvent {
    private UUID courseId;
    private int version;
    public CourseDeletedEvent(UUID courseId, int version) {
        this.courseId = courseId;
        this.version = version;
    }

    public UUID getCourseId() {
        return courseId;
    }

    @Override
    public EventType type() {
        return EventType.COURSE_CREATED;
    }

    @Override
    public UUID aggregateId() {
        return this.courseId;
    }

    @Override
    public int version() {
        return this.version;
    }

    @Override
    public String serialize() {
        return toString();
    }

    @Override
    public String toString() {
        return "{"
                + "         \"courseId\":" +  "\""+courseId+ "\""
                + ",         \"version\":\"" + version + "\""
                + "}";
    }
}