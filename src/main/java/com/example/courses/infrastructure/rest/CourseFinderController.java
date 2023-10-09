package com.example.courses.infrastructure.rest;

import com.example.courses.application.findAll.CourseFinder;
import com.example.courses.application.findAll.FindCourseQuery;
import com.example.courses.domain.PageableCourseReadModel;
import com.example.courses.domain.service.FindAllCoursesService;
import com.example.shared.infrastructure.cqrs.query.QueryBus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseFinderController {

    private final QueryBus queryBus;
    private final FindAllCoursesService courseFinder;

    public CourseFinderController(QueryBus queryBus, FindAllCoursesService courseFinder) {
        this.queryBus = queryBus;
        this.courseFinder = courseFinder;
    }

    @GetMapping

    public PageableCourseReadModel findAllCourses(@RequestParam(defaultValue = "1") int page,
                                                  @RequestParam(defaultValue = "10") int size) {

        FindCourseQuery findCourseQuery = new FindCourseQuery(page, size);
        CompletableFuture<PageableCourseReadModel> executedQuery = queryBus.executeQuery(findCourseQuery);
        try {
            return executedQuery.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
