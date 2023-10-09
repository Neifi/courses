package com.example.courses.domain.aggregates;

import com.example.courses.domain.entities.CourseFinishedAchievement;
import com.example.courses.domain.entities.CourseModule;
import com.example.courses.domain.entities.CourseStartedAchievement;
import com.example.courses.domain.entities.Creator;
import com.example.courses.domain.events.CourseCreatedEvent;
import com.example.courses.domain.events.CourseDeletedEvent;
import com.example.courses.domain.events.CourseUpdatedEvent;
import com.example.courses.domain.exceptions.AggregateInstantiationException;
import com.example.courses.domain.vo.Email;
import com.example.courses.domain.vo.ModuleContent;
import com.example.shared.domain.DomainEvent;
import com.example.shared.domain.exceptions.AggregateAlreadyDeletedException;
import com.example.shared.domain.exceptions.DeletedAggregateException;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {


    @Test
    public void givenCourseModule_WhenAdding_ThenItShouldBeAddedToModulesList() {
        String name = "name";
        Creator creator = Creator.with(new Email("email@mail.com"));
        UUID moduleID = UUID.randomUUID();
        CourseModule courseModule = new CourseModule(moduleID,
                "Intro",
                Duration.ofMinutes(2),
                ModuleContent.Builder().build());
        Course course = Course.create(creator, name,List.of(courseModule));


        course.addModule(courseModule);

        assertFalse(course.modules().isEmpty());
        assertEquals(courseModule, course.modules().get(0));
    }

    @Test
    public void givenNullCourseModule_WhenApprovingModule_ExceptionShouldBeThrown() {
        String name = "name";
        UUID moduleID = UUID.randomUUID();
        Creator creator = Creator.with(new Email("email@mail.com"));
        CourseModule courseModule = new CourseModule(moduleID,
                "Intro",
                Duration.ofMinutes(2),
                ModuleContent.Builder().build());
        Course course = Course.create(creator, name,List.of(courseModule));

        assertThrows(IllegalArgumentException.class, () -> course.addModule(null));
    }

    @Test
    public void givenCourseWithModules_WhenDeletingModule_ThenItShouldBeDeleted() {
        String name = "name";
        Creator creator = Creator.with(new Email("email@mail.com"));
        CourseModule courseModule = new CourseModule(UUID.randomUUID(),
                "Intro",
                Duration.ofMinutes(2),
                ModuleContent.Builder().build());
        CourseModule courseModule2 = new CourseModule(UUID.randomUUID(),
                "Intro",
                Duration.ofMinutes(2),
                ModuleContent.Builder().build());
        Course course = Course.create(creator, name,List.of(courseModule));
        course.addModule(courseModule2);
        course.deleteModule(courseModule);

        assertFalse(course.modules().contains(courseModule));
        assertTrue(course.modules().contains(courseModule2));
    }

    @Test
    public void givenCourseWithModules_WhenAddingDuplicatedModule_ThenItShouldNotBeAdded() {
        String name = "name";
        Creator creator = Creator.with(new Email("email@mail.com"));
        CourseModule courseModule = new CourseModule(UUID.randomUUID(),
                "Intro",
                Duration.ofMinutes(2),
                ModuleContent.Builder().build());
        Course course = Course.create(creator, name,List.of(courseModule));

        course.addModule(courseModule);

        assertTrue(course.modules().contains(courseModule));
        assertEquals(1,course.modules().size());
    }

    @Test
    public void givenCourseWithOneModules_WhenDeletingModule_ExceptionShouldBeThrown() {
        String name = "name";
        Creator creator = Creator.with(new Email("email@mail.com"));
        UUID moduleID = UUID.randomUUID();
        CourseModule courseModule = new CourseModule(moduleID,
                "Intro",
                Duration.ofMinutes(2),
                ModuleContent.Builder().build());
        Course course = Course.create(creator, name,List.of(courseModule));

        assertThrows(IllegalStateException.class,() -> course.deleteModule(courseModule));
        assertFalse(course.modules().isEmpty());
    }

    @Test
    public void givenNullModule_WhenDeletingModule_ExceptionShouldBeThrown() {
        String name = "name";
        Creator creator = Creator.with(new Email("email@mail.com"));
        UUID moduleID = UUID.randomUUID();
        CourseModule courseModule = new CourseModule(moduleID,
                "Intro",
                Duration.ofMinutes(2),
                ModuleContent.Builder().build());
        Course course = Course.create(creator, name,List.of(courseModule));
        course.addModule(courseModule);

        assertThrows(IllegalArgumentException.class, () -> course.deleteModule(null));
    }

    @Test
    public void givenCourseName_WhenCreatingCourse_ThenItShouldBeCreatedWithAchievementsModulesAndCreator() {
        String name = "DDD";
        Creator creator = Creator.with(new Email("email@mail.com"));
        UUID moduleID = UUID.randomUUID();
        CourseModule courseModule = new CourseModule(moduleID,
                "Intro",
                Duration.ofMinutes(2),
                ModuleContent.Builder().build());

        Course course = Course.create(creator, name,List.of(courseModule));
        DomainEvent expectedCourseCreatedEvent = new CourseCreatedEvent(course.id(),
                creator, name,
                List.of(
                        CourseStartedAchievement.create(),
                        CourseFinishedAchievement.create()
                ),
                1
        );

        assertEquals(name, course.name());
        assertEquals(creator, course.creator());
        Collection<DomainEvent> domainEvents = course.pullDomainEvents();
        assertFalse(domainEvents.isEmpty());
        assertEquals(creator, course.creator());
        assertEquals(expectedCourseCreatedEvent.toString(), domainEvents.stream().toList().get(0).toString());
    }

    @Test
    public void givenInvalidCourseName_WhenCreatingCourse_ThenExceptionShouldBeThrown() {
        String name = "";
        UUID moduleID = UUID.randomUUID();
        CourseModule courseModule = new CourseModule(moduleID,
                "Intro",
                Duration.ofMinutes(2),
                ModuleContent.Builder().build());

        Creator creator = Creator.with(new Email("email@mail.com"));
        assertThrows(IllegalArgumentException.class, () -> Course.create(creator, name,List.of(courseModule)));
    }

    @Test
    public void givenNullCourseCreator_WhenCreatingCourse_ThenExceptionShouldBeThrown() {
        UUID moduleID = UUID.randomUUID();
        CourseModule courseModule = new CourseModule(moduleID,
                "Intro",
                Duration.ofMinutes(2),
                ModuleContent.Builder().build());
        String name = "name";
        assertThrows(IllegalArgumentException.class, () -> Course.create(null, name,List.of(courseModule)));
    }

    @Test
    public void givenValidCourseName_WhenUpdatingCourse_ThenNameShouldBeUpdated() {
        String name = "name";
        Creator creator = Creator.with(new Email("email@mail.com"));
        UUID moduleID = UUID.randomUUID();
        CourseModule courseModule = new CourseModule(moduleID,
                "Intro",
                Duration.ofMinutes(2),
                ModuleContent.Builder().build());
        Course course = Course.create(creator, name,List.of(courseModule));

        String newName = "name2";
        course.updateName(newName);

        assertEquals(newName, course.name());
    }

    @Test
    public void givenInvalidCourseName_WhenUpdatingCourse_ThenExceptionShouldBeThrown() {
        String name = "name";
        Creator creator = Creator.with(new Email("email@mail.com"));
        UUID moduleID = UUID.randomUUID();
        CourseModule courseModule = new CourseModule(moduleID,
                "Intro",
                Duration.ofMinutes(2),
                ModuleContent.Builder().build());
        Course course = Course.create(creator, name,List.of(courseModule));

        assertThrows(IllegalArgumentException.class, () -> {
            course.updateName("");
        });
    }

    @Test
    public void givenEvent_WhenRehydrate_ThenCourseShouldBeCreated() {
        UUID courseId = UUID.randomUUID();
        Creator creator = Creator.with(new Email("email@mail.com"));
        DomainEvent courseCreatedEvent = new CourseCreatedEvent(courseId,
                creator, "name",
                List.of(
                        CourseStartedAchievement.create(),
                        CourseFinishedAchievement.create()
                ),
                1
        );
        String updatedName = "Updated Name";
        DomainEvent courseUpdatedEvent = new CourseUpdatedEvent(courseId, updatedName, 2);

        Course course = Course.rehydrateFromEvents(List.of(courseCreatedEvent, courseUpdatedEvent));

        assertEquals(updatedName, course.name());
        assertEquals(courseId, course.id());
    }

    @Test
    public void givenDeletedEvent_WhenRehydrate_ThenCourseShouldBeDeleted() {
        UUID courseId = UUID.randomUUID();
        Creator creator = Creator.with(new Email("email@mail.com"));
        DomainEvent courseCreatedEvent = new CourseCreatedEvent(courseId,
                creator, "name",
                List.of(
                        CourseStartedAchievement.create(),
                        CourseFinishedAchievement.create()
                ),
                1
        );

        DomainEvent courseDeletedEvent = new CourseDeletedEvent(courseId, 2);
        Course course = Course.rehydrateFromEvents(List.of(courseCreatedEvent, courseDeletedEvent));

        assertTrue(course.deleted());
    }

    @Test
    public void givenDeletedCourse_WhenUpdating_ThenExceptionShouldBeThrown() {
        Creator creator = Creator.with(new Email("email@mail.com"));
        UUID moduleID = UUID.randomUUID();
        CourseModule courseModule = new CourseModule(moduleID,
                "Intro",
                Duration.ofMinutes(2),
                ModuleContent.Builder().build());
        Course course = Course.create(creator, "course",List.of(courseModule));
        course.delete();

        assertThrows(DeletedAggregateException.class, () -> course.updateName("name"));
    }

    @Test
    public void givenDeletedCourse_WhenDeleting_ThenExceptionShouldBeThrown() {
        Creator creator = Creator.with(new Email("email@mail.com"));
        UUID moduleID = UUID.randomUUID();
        CourseModule courseModule = new CourseModule(moduleID,
                "Intro",
                Duration.ofMinutes(2),
                ModuleContent.Builder().build());
        Course course = Course.create(creator, "course",List.of(courseModule));
        course.delete();

        assertThrows(AggregateAlreadyDeletedException.class, course::delete);
    }

    @Test
    public void givenEvents_WhenRehydrateWithNoCreatedEvent_ThenExceptionShouldBeThrown() {
        UUID courseId = UUID.randomUUID();

        DomainEvent courseUpdatedEvent = new CourseUpdatedEvent(courseId, "name", 2);


        assertThrows(AggregateInstantiationException.class, () -> {
            Course.rehydrateFromEvents(List.of(courseUpdatedEvent));
        });
    }

    @Test
    public void givenEventApplied_WhenTryToApplyTheSameEvent_ThenStateShouldNotChange() {
        UUID courseId = UUID.randomUUID();
        Creator creator = Creator.with(new Email("email@mail.com"));
        DomainEvent courseCreatedEvent = new CourseCreatedEvent(courseId,
                creator, "name",
                List.of(
                        CourseStartedAchievement.create(),
                        CourseFinishedAchievement.create()
                ),
                1
        );
        String name = "name";
        CourseUpdatedEvent courseUpdatedEvent = new CourseUpdatedEvent(courseId, name, 2);
        CourseUpdatedEvent courseUpdatedEvent2 = new CourseUpdatedEvent(courseId, "name2", 2);
        List<DomainEvent> domainEvents = List.of(courseCreatedEvent, courseUpdatedEvent, courseUpdatedEvent2);

        Course course = Course.rehydrateFromEvents(domainEvents);


        assertEquals(name, course.name());
        assertEquals(2, course.version());
    }

    @Test
    public void givenNewerEventApplied_WhenOlderEventIsApplied_ThenStateShouldNotChange() {
        UUID courseId = UUID.randomUUID();
        Creator creator = Creator.with(new Email("email@mail.com"));
        DomainEvent courseCreatedEvent = new CourseCreatedEvent(courseId,
                creator, "name",
                List.of(
                        CourseStartedAchievement.create(),
                        CourseFinishedAchievement.create()
                ),
                1
        );
        int newerVersion = 2;
        String newerName = "Newer Name";
        CourseUpdatedEvent newerEvent = new CourseUpdatedEvent(courseId, newerName, newerVersion);
        CourseUpdatedEvent olderEvent = new CourseUpdatedEvent(courseId, "Older Name", newerVersion - 1);
        List<DomainEvent> domainEvents = List.of(courseCreatedEvent, newerEvent, olderEvent);

        Course course = Course.rehydrateFromEvents(domainEvents);


        assertEquals(newerName, course.name(), "Course name should not change after applying older event");
        assertEquals(newerVersion, course.version(), "Course version should not change after applying older event");
    }

}