package com.example.courses.infrastructure.persistence.nossql;

import com.example.shared.domain.JsonSerDe;
import com.example.shared.infrastructure.V1CourseCreatedInfrastructureEvent;
import com.example.shared.infrastructure.InfrastructureEvent;
import com.example.shared.infrastructure.InfrastructureEventBus;
import java.util.function.Function;

public class MongoJPACourseCreatedEventHandler implements Function<V1CourseCreatedInfrastructureEvent, Void> {
    private final MongoCourseRepository mongoCourseRepository;
    private final InfrastructureEventBus infrastructureEventBus;
    private final JsonSerDe<CourseMongoModel> jsonSerDe;

    public MongoJPACourseCreatedEventHandler(MongoCourseRepository mongoCourseRepository,
                                             InfrastructureEventBus infrastructureEventBus,
                                             JsonSerDe<CourseMongoModel> jsonSerDe) {
        this.mongoCourseRepository = mongoCourseRepository;
        this.infrastructureEventBus = infrastructureEventBus;
        this.jsonSerDe = jsonSerDe;
        infrastructureEventBus.subscribe(V1CourseCreatedInfrastructureEvent.class, this::handleEvent);

    }

    private Void handleEvent(InfrastructureEvent event) {
        if (event instanceof V1CourseCreatedInfrastructureEvent) {
            apply((V1CourseCreatedInfrastructureEvent) event);
        }
        return null;
    }

    private void save(V1CourseCreatedInfrastructureEvent v1CourseCreatedInfrastructureEvent) {
        String payload = v1CourseCreatedInfrastructureEvent.payload();
        CourseMongoModel courseMongoModel = jsonSerDe.deserialize(payload, CourseMongoModel.class);
        mongoCourseRepository.save(courseMongoModel);

    }


    @Override
    public Void apply(V1CourseCreatedInfrastructureEvent event) {
        save(event);
        return null;
    }
}

