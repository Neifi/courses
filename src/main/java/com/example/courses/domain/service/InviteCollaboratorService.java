package com.example.courses.domain.service;

import java.util.UUID;

public interface InviteCollaboratorService {
    void inviteCollaborator(UUID courseID, String collaboratorEmail);
}
