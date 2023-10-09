package com.example.courses.infrastructure.rest;

import com.example.courses.application.findAll.CourseFinder;
import com.example.courses.application.findById.FindCourseByIdQuery;
import com.example.courses.domain.CourseReadModel;
import com.example.shared.infrastructure.cqrs.query.QueryBus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseByIdFinderController {

    private final QueryBus queryBus;

    public CourseByIdFinderController(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseReadModel> findCourseById(@PathVariable UUID id) {
        try {
            CompletableFuture<CourseReadModel> result = queryBus.executeQuery(new FindCourseByIdQuery(id));
            return ResponseEntity.ok(result.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
