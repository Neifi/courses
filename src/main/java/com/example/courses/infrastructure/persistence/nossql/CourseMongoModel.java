package com.example.courses.infrastructure.persistence.nossql;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "courses")
public class CourseMongoModel {

    @Id
    private UUID courseId;

    @Field(name = "name")
    private String courseName;

    @Field(name = "achievements")
    private List<AchievementMongoModel> achievements = new ArrayList<>();

    public CourseMongoModel(String courseName, List<AchievementMongoModel> achievements) {
        this.courseId = UUID.randomUUID();
        this.courseName = courseName;
        this.achievements = achievements;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<AchievementMongoModel> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<AchievementMongoModel> achievementMongoModels) {
        this.achievements = achievementMongoModels;
    }
}
