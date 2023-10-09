package com.example.courses.domain;

import com.example.courses.domain.entities.Collaborator;
import com.example.courses.domain.entities.CollaboratorStatus;
import com.example.courses.domain.vo.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {

    @Test
    void givenCollaborator_WhenCreated_ThenStatusShouldBePending() {
        Collaborator collaborator = Collaborator.with(new Email("email@email.com"));

        assertEquals(CollaboratorStatus.PENDING,collaborator.status());
    }


    @Test
    void givenCollaborator_WhenRejectRequest_ThenStatusShouldBeRejected() {
        Collaborator collaborator = Collaborator.with(new Email("email@email.com"));

        collaborator.reject();

        assertEquals(CollaboratorStatus.REJECTED,collaborator.status());
    }

    @Test
    void givenCollaborator_WhenRejectAccepted_ThenStatusShouldBeAccepted() {
        Collaborator collaborator = Collaborator.with(new Email("email@email.com"));

        collaborator.accept();

        assertEquals(CollaboratorStatus.ACCEPTED,collaborator.status());
    }

}