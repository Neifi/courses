package com.example.courses.domain.events;

import com.example.courses.domain.vo.Achievement;
import com.example.courses.domain.entities.Creator;
import com.example.shared.domain.DomainEvent;

import java.util.List;
import java.util.UUID;

public class CourseCreatedEvent extends DomainEvent {
    private UUID courseId;
    private String courseName;

    private List<Achievement> achievements;
    private Creator creator;
    private int version;

    public CourseCreatedEvent(UUID courseId, Creator creator, String courseName, List<Achievement> achievements, int version) {
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

    public List<Achievement> achievements() {
        return achievements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseCreatedEvent that)) return false;

        if (version != that.version) return false;
        if (!courseId.equals(that.courseId)) return false;
        if (!courseName.equals(that.courseName)) return false;
        return achievements.equals(that.achievements);
    }

    @Override
    public int hashCode() {
        int result = courseId.hashCode();
        result = 31 * result + courseName.hashCode();
        result = 31 * result + achievements.hashCode();
        result = 31 * result + version;
        return result;
    }
}