package com.example.courses.application.findById;

import com.example.courses.domain.CourseReadModel;
import com.example.courses.domain.service.GetCourseByIdService;
import com.example.shared.infrastructure.cqrs.query.QueryHandler;

public class FindCourseByIdQueryHandler implements QueryHandler<FindCourseByIdQuery,CourseReadModel> {

    private final GetCourseByIdService courseByIdFinder;

    public FindCourseByIdQueryHandler(GetCourseByIdService courseByIdFinder) {
        this.courseByIdFinder = courseByIdFinder;
    }

    @Override
    public CourseReadModel apply(FindCourseByIdQuery findCourseByIdQuery) {
        return courseByIdFinder.getCourseById(findCourseByIdQuery.id());
    }
}
