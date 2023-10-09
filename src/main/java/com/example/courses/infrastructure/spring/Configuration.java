package com.example.courses.infrastructure.spring;

import com.example.courses.application.*;
import com.example.courses.application.create.CourseCreator;
import com.example.courses.application.findAll.CourseFinder;
import com.example.courses.application.findById.CourseByIdFinder;
import com.example.courses.application.remove.CourseRemover;
import com.example.courses.application.update.CourseUpdater;
import com.example.courses.domain.CourseReadRepository;
import com.example.courses.domain.events.CourseCreatedEvent;
import com.example.courses.domain.events.CourseDeletedEvent;
import com.example.courses.domain.events.CourseUpdatedEvent;
import com.example.courses.domain.service.*;
import com.example.courses.infrastructure.persistence.nossql.*;
import com.example.shared.domain.*;
import com.example.courses.infrastructure.persistence.sql.*;
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
    public MongoJPACourseCreatedEventHandler mongoJPACourseCreatedEventHandler(
            MongoCourseRepository mongoCourseRepository,
            InfrastructureEventBus infrastructureEventBus,
            JsonSerDe<CourseMongoModel> jsonSerDe
    ) {
        return new MongoJPACourseCreatedEventHandler(mongoCourseRepository, infrastructureEventBus, jsonSerDe);
    }

    @Bean
    public MongoJPACourseDeletedEventHandler mongoJPACourseDeletedEventHandler(
            MongoCourseRepository mongoCourseRepository,
            InfrastructureEventBus infrastructureEventBus) {
        return new MongoJPACourseDeletedEventHandler(mongoCourseRepository, infrastructureEventBus);
    }

    @Bean
    public MongoJPACourseUpdatedEventHandler mongoJPACourseUpdatedEventHandler(
            MongoCourseRepository mongoCourseRepository,
            InfrastructureEventBus infrastructureEventBus
    ) {
        return new MongoJPACourseUpdatedEventHandler(mongoCourseRepository, infrastructureEventBus);
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
    public FindAllCoursesService courseFinder(CourseReadRepository courseReadRepository) {
        return new CourseFinder(courseReadRepository);
    }

    @Bean
    public GetCourseByIdService courseByIdFinder(CourseReadRepository courseReadRepository) {
        return new CourseByIdFinder(courseReadRepository);
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
            UpdateCourseService updateCourseService,
            GetCourseByIdService getCourseByIdService,
            FindAllCoursesService findAllCoursesService
    ) {
        return new CourseServiceImpl(
                createCourseService,
                deleteCourseService,
                updateCourseService,
                getCourseByIdService,
                findAllCoursesService
        );
    }
}
