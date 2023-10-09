package com.example.courses.domain.vo;

import com.example.courses.domain.exceptions.MaxPointsPerAchievementException;

import java.util.Objects;

public class Achievement {
    private static final int MAX_POINTS = 10;
    private String name;
    private Points points;

    private Achievement() {
    }

    protected Achievement(String name, Points points) {
        this.name = name;
        this.points = points;
    }

    public static Achievement create(String name, int points) {
        if (points > MAX_POINTS) {
            throw new MaxPointsPerAchievementException("max point per achievement is " + MAX_POINTS);
        }
        return new Achievement(name, new Points(points));
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
