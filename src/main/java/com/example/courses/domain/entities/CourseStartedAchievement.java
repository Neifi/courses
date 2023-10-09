package com.example.courses.domain.entities;

import com.example.courses.domain.vo.Achievement;
import com.example.courses.domain.vo.Points;

public class CourseStartedAchievement extends Achievement {

    public CourseStartedAchievement(String name, Points points) {
        super(name, points);
    }

    public static CourseStartedAchievement create() {
        return new CourseStartedAchievement("course started", new Points(10));
    }

}
