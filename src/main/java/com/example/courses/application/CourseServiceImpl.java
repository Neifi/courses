package com.example.courses.application;

import com.example.courses.domain.aggregates.Course;
import com.example.courses.domain.entities.CourseModule;
import com.example.courses.domain.service.CourseService;
import com.example.courses.domain.service.*;

import java.util.List;
import java.util.UUID;

public class CourseServiceImpl implements CourseService {

    private final CreateCourseService createCourseService;
    private final DeleteCourseService deleteCourseService;
    private final UpdateCourseService updateCourseService;
    private final InviteCollaboratorService inviteCollaboratorService;

    public CourseServiceImpl(CreateCourseService createCourseService,
                             DeleteCourseService deleteCourseService,
                             UpdateCourseService updateCourseService, InviteCollaboratorService inviteCollaboratorService) {
        this.createCourseService = createCourseService;
        this.deleteCourseService = deleteCourseService;
        this.updateCourseService = updateCourseService;
        this.inviteCollaboratorService = inviteCollaboratorService;
    }

    @Override
    public Course createCourse(String creatorEmail, String name, List<CourseModule> modules) {
        return createCourseService.createCourse(creatorEmail,name,modules);
    }

    @Override
    public void updateCourse(UUID id, String courseName) {
        updateCourseService.updateCourse(id, courseName);
    }

    @Override
    public void deleteCourse(UUID id) {
        deleteCourseService.deleteCourse(id);
    }

    @Override
    public void inviteCollaborator(UUID courseID, String collaboratorEmail) {
        inviteCollaboratorService.inviteCollaborator(courseID,collaboratorEmail);
    }
}
