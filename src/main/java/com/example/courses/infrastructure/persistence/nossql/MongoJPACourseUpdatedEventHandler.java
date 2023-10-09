package com.example.courses.infrastructure.persistence.nossql;

import com.example.courses.application.exception.CourseNotFoundException;
import com.example.courses.domain.events.CourseUpdatedEvent;
import com.example.shared.domain.JsonSerDe;
import com.example.shared.infrastructure.InfrastructureEvent;
import com.example.shared.infrastructure.InfrastructureEventBus;
import com.example.shared.infrastructure.V1CourseCreatedInfrastructureEvent;
import com.example.shared.infrastructure.V1CourseUpdatedInfrastructureEvent;
import com.google.gson.Gson;

import java.util.function.Function;

public class MongoJPACourseUpdatedEventHandler implements Function<V1CourseUpdatedInfrastructureEvent, Void> {
    private final MongoCourseRepository mongoCourseRepository;
    private final InfrastructureEventBus infrastructureEventBus;

    public MongoJPACourseUpdatedEventHandler(MongoCourseRepository mongoCourseRepository,
                                             InfrastructureEventBus infrastructureEventBus) {
        this.mongoCourseRepository = mongoCourseRepository;
        this.infrastructureEventBus = infrastructureEventBus;
        infrastructureEventBus.subscribe(V1CourseUpdatedInfrastructureEvent.class, this::handleEvent);

    }

    private Void handleEvent(InfrastructureEvent event) {
        if (event instanceof V1CourseUpdatedInfrastructureEvent) {
            apply((V1CourseUpdatedInfrastructureEvent) event);
        }
        return null;
    }

    private void save(V1CourseUpdatedInfrastructureEvent v1CourseUpdatedInfrastructureEvent) {
        String payload = v1CourseUpdatedInfrastructureEvent.payload();
        Gson gson = new Gson();
        CourseUpdatedEvent courseUpdatedEvent = gson.fromJson(payload, CourseUpdatedEvent.class);
        mongoCourseRepository.findById(courseUpdatedEvent.getCourseId()).ifPresentOrElse(
                courseMongoModel -> {
                    courseMongoModel.setCourseName(courseUpdatedEvent.getUpdatedCourseName());
                    mongoCourseRepository.save(courseMongoModel);
                }, () -> {
                    throw new CourseNotFoundException();
                }
        );
    }

    @Override
    public Void apply(V1CourseUpdatedInfrastructureEvent event) {
        save(event);
        return null;
    }
}

