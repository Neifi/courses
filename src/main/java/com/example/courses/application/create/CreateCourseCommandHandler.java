package com.example.courses.application.create;

import com.example.courses.domain.service.CreateCourseService;
import com.example.shared.infrastructure.cqrs.command.CommandHandler;

import java.util.concurrent.CompletableFuture;

public class CreateCourseCommandHandler implements CommandHandler<CompletableFuture<Void>,CreateCourseCommand> {

    private final CreateCourseService createCourseService;

    public CreateCourseCommandHandler(CreateCourseService createCourseService) {
        this.createCourseService = createCourseService;
    }

    @Override
    public CompletableFuture<Void> apply(CreateCourseCommand command) {
        return CompletableFuture.runAsync(() -> createCourseService.createCourse(command.courseName(),command.courseName()));
    }
}
