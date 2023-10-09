package com.example.courses.infrastructure.persistence.mapper;

import com.example.courses.infrastructure.persistence.nossql.AchievementMongoModel;
import com.google.gson.*;

import java.lang.reflect.Type;

public class AchievementMongoModelDeserializer implements JsonDeserializer<AchievementMongoModel> {

    @Override
    public AchievementMongoModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject achievementObject = json.getAsJsonObject().getAsJsonObject("Achievement");
        String name = achievementObject.get("name").getAsString();
        int points = achievementObject.get("points").getAsInt();
        return new AchievementMongoModel(name, points);
    }
}
