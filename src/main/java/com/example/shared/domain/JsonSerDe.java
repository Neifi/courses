package com.example.shared.domain;

public abstract class JsonSerDe<D>  {
    public abstract D deserialize(String origin,Class<D> dest);
    public abstract String serialize(Object object);
}
