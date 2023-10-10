package com.example.courses.infrastructure.rest;

import com.example.courses.application.create.CreateCourseCommand;
import com.example.courses.infrastructure.rest.dto.CourseJsonRequest;
import com.example.shared.infrastructure.cqrs.command.CommandBus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseCreatorController {

    private CommandBus commandBus;

    public CourseCreatorController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PostMapping()
    public ResponseEntity<?> createCourse(@RequestBody CourseJsonRequest request) {
        CreateCourseCommand createCourseCommand = new CreateCourseCommand(request.creatorEmail(),request.name(),request.modules());
        commandBus.executeCommand(createCourseCommand);

        return ResponseEntity.status(201).build();
    }
}
