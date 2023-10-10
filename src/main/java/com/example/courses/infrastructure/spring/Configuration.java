package com.example.courses.infrastructure.spring;

import com.example.courses.application.CourseServiceImpl;
import com.example.courses.application.create.CourseCreator;
import com.example.courses.application.invite.CollaboratorInviter;
import com.example.courses.application.remove.CourseRemover;
import com.example.courses.application.update.CourseUpdater;
import com.example.courses.domain.events.CourseCreatedEvent;
import com.example.courses.domain.events.CourseDeletedEvent;
import com.example.courses.domain.events.CourseUpdatedEvent;
import com.example.courses.domain.repositories.CollaboratorRepository;
import com.example.courses.domain.repositories.CourseRepository;
import com.example.courses.domain.service.*;
import com.example.courses.infrastructure.persistence.sql.*;
import com.example.shared.domain.DomainEvent;
import com.example.shared.domain.DomainEventBus;
import com.example.shared.domain.InMemoryDomainEventBus;
import com.example.shared.domain.JsonSerDe;
import com.example.shared.infrastructure.InMemoryInfrastructureEventBus;
import com.example.shared.infrastructure.InfrastructureEventBus;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public DomainEventBus domainEventBus() {
        return new InMemoryDomainEventBus();
    }

    @Bean
    public InfrastructureEventBus infrastructureEventBus() {
        return new InMemoryInfrastructureEventBus();
    }

    @Bean
    public JPACourseCreatedEventHandler jpaCourseCreatedEventHandler(
            JpaDomainEventRepository jpaDomainEventRepository,
            InfrastructureEventBus infrastructureEventBus,
            DomainEventBus eventBus,
            JsonSerDe<CourseCreatedEvent> jsonSerDe) {
        return new JPACourseCreatedEventHandler(jpaDomainEventRepository,
                eventBus,
                infrastructureEventBus,
                jsonSerDe);
    }

    @Bean
    public JPACourseUpdatedEventHandler jpaCourseUpdatedEventHandler(
            JpaDomainEventRepository jpaDomainEventRepository,
            DomainEventBus eventBus,
            InfrastructureEventBus infrastructureEventBus,

            JsonSerDe<CourseUpdatedEvent> jsonSerDe) {
        return new JPACourseUpdatedEventHandler(jpaDomainEventRepository, eventBus, infrastructureEventBus, jsonSerDe);
    }

    @Bean
    public JPACourseDeletedEventHandler jpaCourseDeletedEventHandler(
            JpaDomainEventRepository jpaDomainEventRepository,
            DomainEventBus eventBus,
            InfrastructureEventBus infrastructureEventBus,
            JsonSerDe<CourseDeletedEvent> jsonSerDe) {
        return new JPACourseDeletedEventHandler(jpaDomainEventRepository, eventBus, infrastructureEventBus, jsonSerDe);
    }

    @Bean
    public CourseRepository courseRepository(DomainEventBus domainEventBus, JpaDomainEventRepository jpaDomainEventRepository) {
        return new CourseRepositoryImpl(domainEventBus, jpaDomainEventRepository);
    }

    @Bean
    public CreateCourseService courseCreator(CourseRepository courseRepository) {
        return new CourseCreator(courseRepository);
    }

    @Bean
    public UpdateCourseService courseUpdater(DomainEventBus domainEventBus,
                                             JpaDomainEventRepository jpaDomainEventRepository,
                                             JsonSerDe<DomainEvent> jsonSerDe,
                                             CourseRepository courseRepository) {
        return new CourseUpdater(domainEventBus, jpaDomainEventRepository, jsonSerDe, courseRepository);
    }

    @Bean
    public DeleteCourseService courseRemover(JpaDomainEventRepository jpaDomainEventRepository,
                                             JsonSerDe<DomainEvent> jsonSerDe,
                                             CourseRepository courseRepository) {
        return new CourseRemover(jpaDomainEventRepository, jsonSerDe, courseRepository);
    }

    @Bean
    public CollaboratorRepository collaboratorRepository(){
        return new CollaboratorRepositoryImpl();
    }

    @Bean
    public InviteCollaboratorService inviteCollaboratorService(CourseRepository courseRepository,CollaboratorRepository collaboratorRepository) {
        return new CollaboratorInviter(courseRepository,collaboratorRepository);
    }

    @Bean
    public CourseService courseService(
            CreateCourseService createCourseService,
            DeleteCourseService deleteCourseService,
            UpdateCourseService updateCourseService,
            InviteCollaboratorService inviteCollaboratorService
    ) {
        return new CourseServiceImpl(
                createCourseService,
                deleteCourseService,
                updateCourseService,
                inviteCollaboratorService);
    }
}
