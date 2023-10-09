package com.example.courses.application.findAll;

import com.example.courses.domain.PageableCourseReadModel;
import com.example.courses.domain.service.FindAllCoursesService;
import com.example.shared.infrastructure.cqrs.query.QueryHandler;

public class FindCourseQueryHandler implements QueryHandler<FindCourseQuery,PageableCourseReadModel> {

    private final FindAllCoursesService courseFinder;

    public FindCourseQueryHandler(FindAllCoursesService courseFinder) {
        this.courseFinder = courseFinder;
    }

    @Override
    public PageableCourseReadModel apply(FindCourseQuery query) {
        return courseFinder.findAllCourses(query.page(),query.pageSize());
    }
}
