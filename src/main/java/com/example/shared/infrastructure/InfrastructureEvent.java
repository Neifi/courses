package com.example.shared.infrastructure;

import java.time.Instant;
import java.util.Map;

public interface InfrastructureEvent {
    String id();

    String type();

    Instant timestamp();

    int version();

    String payload();
    Map<String,String> metadata();
}
