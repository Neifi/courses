package com.example.courses.application.create;

import com.example.shared.infrastructure.cqrs.command.Command;

public record CreateCourseCommand(String creatorEmail,String courseName) implements Command {
}
