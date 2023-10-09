package com.example.courses.infrastructure.persistence.mapper;

import com.example.shared.domain.DomainEvent;
import com.example.shared.domain.JsonSerDe;
import com.google.gson.Gson;

public class GsonDomainEventJsonSerDe<D extends DomainEvent> extends JsonSerDe<D> {

    private final Gson gson = new Gson();

    @Override
    public D deserialize(String origin, Class<D> dest) {
        return gson.fromJson(origin, dest);
    }

    @Override
    public String serialize(Object object) {
        return gson.toJson(object);
    }
}
