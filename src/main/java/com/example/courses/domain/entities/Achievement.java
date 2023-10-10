package com.example.courses.domain.entities;

import com.example.courses.domain.exceptions.MaxPointsPerAchievementException;
import com.example.courses.domain.vo.Points;

import java.util.Objects;
import java.util.UUID;

public class Achievement {
    private static final int MAX_POINTS = 100;
    private UUID achievementID;
    private String name;
    private Points points;

    private Achievement() {
    }

    public Achievement(UUID achievementID, String name, Points points) {
        this.achievementID = achievementID;
        if (points.quantity() > MAX_POINTS) {
            throw new MaxPointsPerAchievementException("max point per achievement is " + MAX_POINTS);
        }
        this.name = name;
        this.points = points;
    }

    public static  Achievement courseStarted() {
        return new Achievement(UUID.randomUUID(),"Course Started",new Points(50));
    }

    public static  Achievement courseFinished() {
        return new Achievement(UUID.randomUUID(),"Course Finished",new Points(100));
    }

    public String name() {
        return name;
    }

    public int points() {
        return points.quantity();
    }

    @Override
    public String toString() {
        return "{\"Achievement\":{"
                + "        \"name\":\"" + name + "\""
                + ",         \"points\":" + points.quantity()
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Achievement that = (Achievement) o;

        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }
}
