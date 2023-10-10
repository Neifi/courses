package com.example.courses.application.create;

import com.example.courses.domain.entities.CourseModule;
import com.example.shared.infrastructure.cqrs.command.Command;

import java.util.List;

public record CreateCourseCommand(String creatorEmail, String courseName, List<CourseModule> modules) implements Command {
}
