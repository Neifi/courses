package com.example.courses.domain.service;

import com.example.courses.domain.vo.Achievement;

import java.util.List;
import java.util.UUID;

public interface AddAchievementsService {
    void addAchievements(UUID courseId, List<Achievement> achievements);
}
