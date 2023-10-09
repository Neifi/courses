package com.example.courses.infrastructure.streaming;

import com.example.shared.infrastructure.V1CourseCreatedInfrastructureEvent;
import com.example.shared.infrastructure.InfrastructureEvent;
import com.example.shared.infrastructure.InfrastructureEventBus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CourseCreatedEventsHandler implements Function<V1CourseCreatedInfrastructureEvent,Void> {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final InfrastructureEventBus eventBus;


    public CourseCreatedEventsHandler(KafkaTemplate<String, String> kafkaTemplate, InfrastructureEventBus eventBus) {
        this.kafkaTemplate = kafkaTemplate;
        this.eventBus = eventBus;
        eventBus.subscribe(V1CourseCreatedInfrastructureEvent.class, this::handleEvent);
    }

    private Void handleEvent(InfrastructureEvent event) {
        if (event instanceof V1CourseCreatedInfrastructureEvent) {
            apply((V1CourseCreatedInfrastructureEvent) event);
        }
        return null;
    }


    @Override
    public Void apply(V1CourseCreatedInfrastructureEvent event) {
        kafkaTemplate.send("courses-topic", event.id(), event.payload());
        return null;
    }
}
