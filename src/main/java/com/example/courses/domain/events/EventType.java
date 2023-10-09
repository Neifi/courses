package com.example.courses.domain.events;

import org.apache.commons.lang3.builder.ToStringBuilder;

public enum EventType {
    COURSE_CREATED,
    COURSE_UPDATED,
    COURSE_DELETED,
    ACHIEVEMENTS_ADDED,
    ;

    @Override
    public String

    toString() {
        return this.name();

    }
}
