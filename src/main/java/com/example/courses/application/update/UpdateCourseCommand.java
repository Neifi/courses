package com.example.courses.application.update;


import com.example.shared.infrastructure.cqrs.command.Command;

import java.util.UUID;

public record UpdateCourseCommand(UUID courseId, String courseName) implements Command {
}
