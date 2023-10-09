package com.example.courses.application.findAll;

import com.example.shared.infrastructure.cqrs.query.Query;

public record FindCourseQuery(int page, int pageSize) implements Query {
}
