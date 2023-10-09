package com.example.courses.infrastructure.persistence.nossql;

import com.example.courses.application.exception.CourseNotFoundException;
import com.example.courses.domain.events.CourseDeletedEvent;
import com.example.shared.infrastructure.InfrastructureEvent;
import com.example.shared.infrastructure.InfrastructureEventBus;
import com.example.shared.infrastructure.V1CourseCreatedInfrastructureEvent;
import com.example.shared.infrastructure.V1CourseDeletedInfrastructureEvent;
import com.google.gson.Gson;

import java.util.function.Function;

public class MongoJPACourseDeletedEventHandler implements Function<V1CourseDeletedInfrastructureEvent, Void> {
    private final MongoCourseRepository mongoCourseRepository;
    private final InfrastructureEventBus infrastructureEventBus;

    public MongoJPACourseDeletedEventHandler(MongoCourseRepository mongoCourseRepository,
                                             InfrastructureEventBus infrastructureEventBus) {
        this.mongoCourseRepository = mongoCourseRepository;
        this.infrastructureEventBus = infrastructureEventBus;
        infrastructureEventBus.subscribe(V1CourseDeletedInfrastructureEvent.class, this::handleEvent);

    }

    private Void handleEvent(InfrastructureEvent event) {
        if (event instanceof V1CourseDeletedInfrastructureEvent) {
            apply((V1CourseDeletedInfrastructureEvent) event);
        }
        return null;
    }

    private void delete(V1CourseDeletedInfrastructureEvent v1CourseDeletedInfrastructureEvent) {
        Gson gson = new Gson();
        CourseDeletedEvent courseDeletedEvent = gson.fromJson(v1CourseDeletedInfrastructureEvent.payload(), CourseDeletedEvent.class);

        mongoCourseRepository.findById(courseDeletedEvent.aggregateId()).ifPresentOrElse((courseMongoModel) -> {
            mongoCourseRepository.deleteById(courseMongoModel.getCourseId());
        }, () -> {
            throw new CourseNotFoundException();
        });
    }


    @Override
    public Void apply(V1CourseDeletedInfrastructureEvent event) {
        delete(event);
        return null;
    }
}

