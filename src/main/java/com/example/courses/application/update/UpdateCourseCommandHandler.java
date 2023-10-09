package com.example.courses.application.update;

import com.example.courses.domain.service.UpdateCourseService;
import com.example.shared.infrastructure.cqrs.command.CommandHandler;

import java.util.concurrent.CompletableFuture;

public class UpdateCourseCommandHandler implements CommandHandler<CompletableFuture<Void>, UpdateCourseCommand> {
    private final UpdateCourseService updateCourseService;

    public UpdateCourseCommandHandler(UpdateCourseService updateCourseService) {
        this.updateCourseService = updateCourseService;
    }

    @Override
    public CompletableFuture<Void> apply(UpdateCourseCommand command) {
        return CompletableFuture
                .runAsync(() -> updateCourseService.updateCourse(command.courseId(), command.courseName()));

    }
}
