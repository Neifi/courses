package com.example.courses.infrastructure.persistence.mapper;

import com.example.courses.infrastructure.persistence.nossql.AchievementMongoModel;
import com.example.courses.infrastructure.persistence.nossql.CourseMongoModel;
import com.example.shared.domain.JsonSerDe;
import com.example.shared.infrastructure.InfrastructureEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonMongoModelJsonSerDe<D extends CourseMongoModel> extends JsonSerDe<D> {

    private final Gson gson;

    public GsonMongoModelJsonSerDe() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AchievementMongoModel.class, new AchievementMongoModelDeserializer());
        gson = gsonBuilder.create();

    }

    @Override
    public D deserialize(String origin, Class<D> dest) {
        return gson.fromJson(origin, dest);
    }

    @Override
    public String serialize(Object object) {
        return gson.toJson(object);
    }
}
