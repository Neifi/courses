package com.example.courses.domain.events;

import com.example.courses.domain.entities.Achievement;
import com.example.courses.domain.entities.Creator;
import com.example.shared.domain.DomainEvent;

import java.util.*;

public class CourseCreatedEvent extends DomainEvent {
    private UUID courseId;
    private String courseName;
    private Creator creator;
    private int version;
    private Set<Achievement> achievements;


    public CourseCreatedEvent(UUID courseId, Creator creator, String courseName, Set<Achievement> achievements, int version) {
        this.courseId = courseId;
        this.creator = creator;
        this.courseName = courseName;
        this.achievements = achievements;
        this.version = version;
    }

    public String courseName() {
        return courseName;
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
                + "         \"courseId\":" + "\""+courseId.toString()+"\""
                + ",         \"courseName\":\"" + courseName + "\""
                + ",         \"achievements\":" + achievements.toString()
                + ",         \"version\":\"" + version + "\""
                + "}";
    }

    public Set<Achievement> achievements() {
        return achievements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseCreatedEvent that)) return false;

        if (version != that.version) return false;
        if (!Objects.equals(courseId, that.courseId)) return false;
        if (!Objects.equals(courseName, that.courseName)) return false;
        if (!Objects.equals(creator, that.creator)) return false;
        return Objects.equals(achievements, that.achievements);
    }

    @Override
    public int hashCode() {
        int result = courseId != null ? courseId.hashCode() : 0;
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + version;
        result = 31 * result + (achievements != null ? achievements.hashCode() : 0);
        return result;
    }
}