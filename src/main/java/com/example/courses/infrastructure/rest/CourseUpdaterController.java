package com.example.courses.infrastructure.rest;

import com.example.courses.application.update.UpdateCourseCommand;
import com.example.courses.infrastructure.rest.dto.CourseJsonRequest;
import com.example.shared.infrastructure.cqrs.command.CommandBus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseUpdaterController {

    private final CommandBus commandBus;

    public CourseUpdaterController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCourse(@RequestBody CourseJsonRequest request, @PathVariable UUID id) {
        UpdateCourseCommand updateCourseCommand = new UpdateCourseCommand(id, request.name());

        try {
            commandBus.executeCommand(updateCourseCommand).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.accepted().build();
    }
}
