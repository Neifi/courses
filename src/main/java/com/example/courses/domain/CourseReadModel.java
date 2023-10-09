package com.example.courses.domain;

import java.util.List;
import java.util.UUID;

public record CourseReadModel(
        UUID id,
        String name,
        List<AchievementsReadModel> achievements
) {

}

