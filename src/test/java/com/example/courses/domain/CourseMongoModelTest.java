package com.example.courses.domain;

import com.example.courses.domain.aggregates.Course;
import com.example.courses.domain.entities.CourseModule;
import com.example.courses.domain.entities.Creator;
import com.example.courses.domain.vo.Email;
import com.example.courses.domain.vo.ModuleContent;
import com.example.shared.domain.exceptions.DeletedAggregateException;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseMongoModelTest {

    @Test
    void GivenCourse_WhenCourseIsDeletedAndTryToBeUpdated_ItShouldThrowException() {
        Creator creator = Creator.with(new Email("email@email.com"));
        UUID moduleID = UUID.randomUUID();
        CourseModule courseModule = new CourseModule(moduleID,
                "Intro",
                Duration.ofMinutes(2),
                ModuleContent.Builder().build());
        Course course = Course.create(creator, "name", List.of(courseModule));

        course.delete();

        assertThrows(DeletedAggregateException.class, () -> {
            course.updateName("otherName");
        });
    }
}