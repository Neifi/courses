package com.example.courses.application.findById;

import com.example.shared.infrastructure.cqrs.query.Query;

import java.util.UUID;

public record FindCourseByIdQuery(UUID id) implements Query {
}
