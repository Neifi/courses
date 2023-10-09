package com.example.courses.application.remove;

import com.example.shared.infrastructure.cqrs.command.Command;

import java.util.UUID;

public record RemoveCourseCommand(
        UUID courseId
) implements Command {
}

