package com.example.courses.infrastructure.rest;

import com.example.courses.application.remove.RemoveCourseCommand;
import com.example.courses.domain.service.CourseService;
import com.example.shared.domain.exceptions.AggregateAlreadyDeletedException;
import com.example.shared.infrastructure.cqrs.command.CommandBus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseRemoverController {


    private final CommandBus commandBus;

    public CourseRemoverController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCourse(@PathVariable UUID id) {

        RemoveCourseCommand removeCourseCommand = new RemoveCourseCommand(id);
        commandBus.executeCommand(removeCourseCommand);

        return ResponseEntity.ok().build();
    }
}
