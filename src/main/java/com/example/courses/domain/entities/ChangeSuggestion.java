package com.example.courses.domain.entities;

import com.example.courses.domain.vo.SuggestionStatus;

import java.util.UUID;

import static com.example.courses.domain.vo.SuggestionStatus.*;

public class ChangeSuggestion {
    private final UUID id;
    private final String description;
    private SuggestionStatus status;

    private UUID courseID;
    public ChangeSuggestion(UUID id, String description) {
        this.id = id;
        this.description = description;
        this.status = PENDING;
    }

    public void addSuggestion(UUID courseID) {
        this.courseID = courseID;
    }
    public void reject() {
        this.status = REJECTED;
    }
    public void approve() {
        this.status = APPROVED;
    }

    public SuggestionStatus status() {
        return this.status;
    }

    public String description() {
        return this.description;
    }

    public UUID id() {
        return this.id;
    }
    public UUID courseID() {
        return this.courseID;
    }
}
