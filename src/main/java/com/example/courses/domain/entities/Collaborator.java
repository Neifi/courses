package com.example.courses.domain.entities;

import com.example.courses.domain.vo.Email;

import java.util.Objects;
import java.util.UUID;

public class Collaborator {

    private final Email email;
    private CollaboratorStatus collaboratorStatus;
    private UUID courseID;

    private Collaborator(Email email){
        this.email = email;
        this.collaboratorStatus = CollaboratorStatus.PENDING;
    }
    public static Collaborator with(Email mail) {
        return new Collaborator(mail);
    }

    public void inviteCollaborator(UUID courseID) {
        this.courseID = courseID;
    }

    public CollaboratorStatus status() {
        return this.collaboratorStatus;
    }

    public void reject() {
        this.collaboratorStatus = CollaboratorStatus.REJECTED;
    }

    public void accept() {
        this.collaboratorStatus = CollaboratorStatus.ACCEPTED;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Collaborator that = (Collaborator) o;

        if (!Objects.equals(email, that.email)) return false;
        return collaboratorStatus == that.collaboratorStatus;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (collaboratorStatus != null ? collaboratorStatus.hashCode() : 0);
        return result;
    }

    public Email email() {
        return this.email;
    }

}
