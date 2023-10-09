package com.example.courses.infrastructure.spring;

import com.example.courses.application.CourseServiceImpl;
import com.example.courses.application.create.CourseCreator;
import com.example.courses.application.remove.CourseRemover;
import com.example.courses.application.update.CourseUpdater;
import com.example.courses.domain.events.CourseCreatedEvent;
import com.example.courses.domain.events.CourseDeletedEvent;
import com.example.courses.domain.events.CourseUpdatedEvent;
import com.example.courses.domain.service.CourseService;
import com.example.courses.domain.service.CreateCourseService;
import com.example.courses.domain.service.DeleteCourseService;
import com.example.courses.domain.service.UpdateCourseService;
import com.example.courses.infrastructure.persistence.sql.JPACourseCreatedEventHandler;
import com.example.courses.infrastructure.persistence.sql.JPACourseDeletedEventHandler;
import com.example.courses.infrastructure.persistence.sql.JPACourseUpdatedEventHandler;
import com.example.courses.infrastructure.persistence.sql.JpaDomainEventRepository;
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
    public CreateCourseService courseCreator(DomainEventBus domainEventBus) {
        return new CourseCreator(domainEventBus);
    }

    @Bean
    public UpdateCourseService courseUpdater(DomainEventBus domainEventBus,
                                             JpaDomainEventRepository jpaDomainEventRepository,
                                             JsonSerDe<DomainEvent> jsonSerDe) {
        return new CourseUpdater(domainEventBus, jpaDomainEventRepository, jsonSerDe);
    }

    @Bean
    public DeleteCourseService courseRemover(DomainEventBus domainEventBus,
                                             JpaDomainEventRepository jpaDomainEventRepository,
                                             JsonSerDe<DomainEvent> jsonSerDe) {
        return new CourseRemover(domainEventBus, jpaDomainEventRepository, jsonSerDe);
    }

    @Bean
    public CourseService courseService(
            CreateCourseService createCourseService,
            DeleteCourseService deleteCourseService,
            UpdateCourseService updateCourseService
    ) {
        return new CourseServiceImpl(
                createCourseService,
                deleteCourseService,
                updateCourseService
        );
    }
}
