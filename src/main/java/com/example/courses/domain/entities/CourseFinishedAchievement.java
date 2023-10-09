package com.example.courses.domain.entities;

import com.example.courses.domain.vo.Achievement;
import com.example.courses.domain.vo.Points;

public class CourseFinishedAchievement extends Achievement {

    public CourseFinishedAchievement(String name, Points points) {
        super(name, points);
    }

    public static CourseFinishedAchievement create() {
        return new CourseFinishedAchievement("course finished", new Points(10));
    }

}
