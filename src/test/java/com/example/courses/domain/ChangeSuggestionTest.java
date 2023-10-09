package com.example.courses.domain;

import com.example.courses.domain.entities.ChangeSuggestion;
import com.example.courses.domain.vo.SuggestionStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ChangeSuggestionTest {


    @Test
    public void givenDescription_WhenCreatingSuggestion_ItShouldBeWithPendingStatus() {
        String description = "change module name";
        UUID id = UUID.randomUUID();
        UUID moduleID = UUID.randomUUID();
        ChangeSuggestion changeSuggestion = new ChangeSuggestion(id, description);

        assertEquals(SuggestionStatus.PENDING, changeSuggestion.status());
        assertEquals(description, changeSuggestion.description());
        assertEquals(id, changeSuggestion.id());
    }

    @Test
    public void givenChangeSuggestion_WhenRejecting_ItShouldBeRejected() {
        ChangeSuggestion changeSuggestion = new ChangeSuggestion(UUID.randomUUID(), "desc");

        changeSuggestion.reject();

        assertEquals(SuggestionStatus.REJECTED, changeSuggestion.status());
    }

    @Test
    public void givenChangeSuggestion_WhenApproving_ItShouldBeApproved() {
        ChangeSuggestion changeSuggestion = new ChangeSuggestion(UUID.randomUUID(), "desc");

        changeSuggestion.approve();

        assertEquals(SuggestionStatus.APPROVED, changeSuggestion.status());
    }


}