package com.example.courses.infrastructure.persistence.nossql;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;


public class AchievementMongoModel {

    @Field(name = "name")
    @JsonProperty("Achievements.Achievement.name")
    private String name;

    @Field(name = "points")
    @JsonProperty("Achievements.Achievement.points")
    private int points;

    public AchievementMongoModel(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
