package com.example.courses.infrastructure.persistence.mapper;

import com.example.courses.domain.events.CourseCreatedEvent;
import com.example.courses.domain.events.CourseDeletedEvent;
import com.example.courses.domain.events.CourseUpdatedEvent;
import com.example.courses.infrastructure.persistence.nossql.CourseMongoModel;
import com.example.shared.domain.DomainEvent;
import com.example.shared.domain.JsonSerDe;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public JsonSerDe<CourseMongoModel> courseMongoModelJsonSerDe() {
        return new GsonMongoModelJsonSerDe<>();
    }

    @Bean
    public JsonSerDe<DomainEvent> domainEventJsonSerDe() {
        return new GsonDomainEventJsonSerDe<>();
    }

    @Bean
    public JsonSerDe<CourseCreatedEvent> courseCreatedJsonSerDe() {
        return new GsonDomainEventJsonSerDe<>();
    }

    @Bean
    public JsonSerDe<CourseUpdatedEvent> courseUpdatedJsonSerDe() {
        return new GsonDomainEventJsonSerDe<>();
    }

    @Bean
    public JsonSerDe<CourseDeletedEvent> courseDeletedJsonSerDe() {
        return new GsonDomainEventJsonSerDe<>();
    }

}
