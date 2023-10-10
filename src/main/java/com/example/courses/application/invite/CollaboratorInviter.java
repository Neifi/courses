package com.example.courses.application.invite;

import com.example.courses.application.exceptions.CourseNotFoundException;
import com.example.courses.domain.aggregates.Course;
import com.example.courses.domain.repositories.CollaboratorRepository;
import com.example.courses.domain.repositories.CourseRepository;
import com.example.courses.domain.service.InviteCollaboratorService;
import com.example.courses.domain.vo.Email;
import com.example.courses.infrastructure.persistence.sql.DomainEventEntity;
import com.example.courses.infrastructure.persistence.sql.DomainEventFactory;
import com.example.courses.infrastructure.persistence.sql.JpaDomainEventRepository;
import com.example.shared.domain.DomainEvent;
import com.example.shared.domain.JsonSerDe;

import java.util.List;
import java.util.UUID;

public class CollaboratorInviter implements InviteCollaboratorService {
    private final CollaboratorRepository collaboratorRepository;
    private final CourseRepository courseRepository;


    public CollaboratorInviter(CourseRepository courseRepository, CollaboratorRepository collaboratorRepository) {
        this.courseRepository = courseRepository;
        this.collaboratorRepository = collaboratorRepository;
    }

    @Override
    public void inviteCollaborator(UUID courseID, String collaboratorEmail) {

        Course course = courseRepository.findById(courseID);

        course.inviteCollaborator(new Email(collaboratorEmail));


    }
}
