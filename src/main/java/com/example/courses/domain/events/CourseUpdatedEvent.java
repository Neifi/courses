package com.example.courses.domain.events;

import com.example.shared.domain.DomainEvent;

import java.util.UUID;

public class CourseUpdatedEvent extends DomainEvent {
    private UUID courseId;
    private String updatedCourseName;

    private int version;

    public CourseUpdatedEvent(UUID courseId, String updatedCourseName, int version) {
        this.courseId = courseId;
        this.updatedCourseName = updatedCourseName;
        this.version = version;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public String getUpdatedCourseName() {
        return updatedCourseName;
    }

    @Override
    public EventType type() {
        return EventType.COURSE_UPDATED;
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
                + ",         \"updatedCourseName\":\"" + updatedCourseName + "\""
                + ",         \"version\":\"" + version + "\""
                + "}";
    }
}