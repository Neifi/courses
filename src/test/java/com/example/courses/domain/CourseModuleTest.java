package com.example.courses.domain;

import com.example.courses.domain.entities.CourseModule;
import com.example.courses.domain.vo.ModuleContent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseModuleTest {

    @Test
    public void givenValidNameAndDuration_WhenCreatingModule_ItShouldBeCreated() {
        String name = "module 1";
        Duration duration = Duration.ofMinutes(120);
        CourseModule courseModule =     new CourseModule(UUID.randomUUID(),name, duration, ModuleContent.Builder().build());

        assertEquals(name, courseModule.name());
        assertEquals(duration, courseModule.duration());
    }

    @ParameterizedTest
    @ValueSource(longs = {
            0,
            -1,
            121})
    public void givenInvalidDuration_WhenCreatingModule_ItShouldThrowException(long values) {
        String name = "module 1";
        Duration duration = Duration.ofDays(values);

        assertThrows(IllegalArgumentException.class, () -> new CourseModule(UUID.randomUUID(),name, duration,ModuleContent.Builder().build()));
    }
}