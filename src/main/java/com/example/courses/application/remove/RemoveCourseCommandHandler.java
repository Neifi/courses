package com.example.courses.application.remove;

import com.example.courses.application.create.CreateCourseCommand;
import com.example.courses.domain.service.DeleteCourseService;
import com.example.shared.infrastructure.cqrs.command.CommandBus;
import com.example.shared.infrastructure.cqrs.command.CommandHandler;

import java.util.concurrent.CompletableFuture;

public class RemoveCourseCommandHandler implements CommandHandler<CompletableFuture<Void>, RemoveCourseCommand> {

    private final DeleteCourseService deleteCourseService;
    public RemoveCourseCommandHandler(DeleteCourseService deleteCourseService) {
        this.deleteCourseService = deleteCourseService;
    }

    @Override
    public CompletableFuture<Void> apply(RemoveCourseCommand command) {
        return CompletableFuture.runAsync(() -> deleteCourseService.deleteCourse(command.courseId()));
    }
}
