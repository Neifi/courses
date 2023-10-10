package com.example.courses.domain.aggregates;

import com.example.courses.domain.entities.CourseModule;
import com.example.courses.domain.entities.Creator;
import com.example.courses.domain.events.*;
import com.example.courses.domain.exceptions.AggregateInstantiationException;
import com.example.courses.domain.entities.Achievement;
import com.example.courses.domain.exceptions.CourseModuleNotFoundException;
import com.example.courses.domain.exceptions.EmptyCourseDomainEventsException;
import com.example.courses.domain.vo.Email;
import com.example.shared.domain.AbstractAggregate;
import com.example.shared.domain.DomainEvent;
import com.example.shared.domain.exceptions.AggregateAlreadyDeletedException;
import com.example.shared.domain.exceptions.DeletedAggregateException;

import java.util.*;

public class Course extends AbstractAggregate<Course> {

    private static final int MAX_MODULES = 10;
    private UUID id;
    private String name;
    private boolean deleted;

    private Creator creator;
    private Set<Achievement> achievements = new HashSet<>();

    private Set<CourseModule> courseModules = new HashSet<>();

    public static Course rehydrateFromEvents(List<DomainEvent> domainEvent) {
        return new Course().apply(domainEvent);
    }

    private Course() {
    }

    private Course(Creator creator, String name, List<CourseModule> courseModules) {
        if (creator == null) {
            throw new IllegalArgumentException("creator cant be empty or null");
        }

        validateCourseName(name);
        validateCourseModules(courseModules);
        courseModules.forEach(this::addToModules);
        achievements.add(Achievement.courseStarted());
        achievements.add(Achievement.courseFinished());
        this.name = name;
        this.id = UUID.randomUUID();
        this.creator = creator;
        ++version;
        registerEvent(new CourseCreatedEvent(id, creator, name, achievements, version));

    }

    private void addToModules(CourseModule courseModule) {
        this.courseModules.add(courseModule);
    }

    private void validateCourseModules(List<CourseModule> courseModules) {
        if (courseModules.isEmpty()) {
            throw new IllegalArgumentException("empty modules");
        }
        if (courseModules.size() >= MAX_MODULES) {
            throw new IllegalArgumentException("maximum number of modules reached");
        }
    }

    public static Course create(Creator creator, String name, List<CourseModule> courseModules) {
        return new Course(creator, name, courseModules);
    }

    public void updateName(String courseName) {
        if (deleted) {
            throw new DeletedAggregateException();
        }
        validateCourseName(courseName);
        this.name = courseName;
        ++version;
        registerEvent(new CourseUpdatedEvent(this.id, courseName, version));
    }

    private void validateCourseName(String courseName) {
        if (courseName == null || courseName.isEmpty()) {
            throw new IllegalArgumentException("course name cant be empty or null");
        }
    }

    public void delete() {
        if (deleted) {
            throw new AggregateAlreadyDeletedException();
        }
        this.deleted = true;
        ++this.version;
        registerEvent(new CourseDeletedEvent(this.id, version));
    }

    public void addModule(CourseModule courseModule) {
        if (courseModule == null) {
            throw new IllegalArgumentException("null module");
        }
        if (this.courseModules.size() >= MAX_MODULES) {
            throw new IllegalArgumentException("maximum number of modules reached");
        }
        if (this.courseModules.contains(courseModule)) {
            return;
        }
        courseModules.add(courseModule);
    }

    public void deleteModule(CourseModule courseModule) {
        if (courseModule == null) {
            throw new IllegalArgumentException("null moduleID");
        }
        if (this.courseModules.size() == 1) {
            throw new IllegalStateException("course must have at least one module");
        }
        courseModules.remove(courseModule);
    }

    public void inviteCollaborator(Email email) {
        registerEvent(new CollaboratorInvitedEvent(this.id, email));
    }


    @Override
    protected Course apply(List<DomainEvent> courseDomainEvent) {
        if (courseDomainEvent.isEmpty()) throw new EmptyCourseDomainEventsException();
        if (courseDomainEvent.stream()
                .anyMatch(domainEvent -> EventType.COURSE_CREATED.equals(domainEvent.type()))) {

            for (DomainEvent domainEvent : courseDomainEvent) {
                if (this.version >= domainEvent.version()) {
                    continue;
                }
                if (domainEvent instanceof CourseCreatedEvent) {
                    apply((CourseCreatedEvent) domainEvent);
                    continue;
                }
                if (domainEvent instanceof CourseUpdatedEvent) {
                    apply((CourseUpdatedEvent) domainEvent);
                    continue;
                }

                if (domainEvent instanceof CourseDeletedEvent) {
                    apply((CourseDeletedEvent) domainEvent);
                }
            }

            return this;
        }
        throw new AggregateInstantiationException("course created event not found");
    }

    private void apply(CourseCreatedEvent courseCreatedEvent) {
        this.id = courseCreatedEvent.aggregateId();
        this.name = courseCreatedEvent.courseName();
        this.version = courseCreatedEvent.version();
        this.achievements = courseCreatedEvent.achievements();
    }

    private void apply(CourseUpdatedEvent courseUpdatedEvent) {
        this.id = courseUpdatedEvent.getCourseId();
        this.name = courseUpdatedEvent.getUpdatedCourseName();
        this.version = courseUpdatedEvent.version();
    }

    private void apply(CourseDeletedEvent courseDeletedEvent) {
        this.id = courseDeletedEvent.getCourseId();
        this.deleted = true;
        this.version = courseDeletedEvent.version();
    }

    public String name() {
        return name;
    }

    public UUID id() {
        return id;
    }

    public int version() {
        return this.version;
    }

    public boolean deleted() {
        return this.deleted;
    }

    public Creator creator() {
        return this.creator;
    }

    public CourseModule moduleById(UUID moduleID) {
        Optional<CourseModule> first = this.courseModules
                .stream()
                .filter(courseModule -> courseModule.moduleID().equals(moduleID))
                .findFirst();
        if (first.isEmpty()) {
            throw new CourseModuleNotFoundException();
        }

        return first.get();
    }

}
