package com.example.shared.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class InMemoryInfrastructureEventBus implements InfrastructureEventBus {
    private final Map<Class<?>, Function<? super InfrastructureEvent, ?>> bus = new HashMap<>();

    public InMemoryInfrastructureEventBus() {
    }


    public <R> void subscribe(Class<? extends InfrastructureEvent> queryClass, Function<? super InfrastructureEvent, R> handler) {
        bus.put(queryClass, handler);
    }

    public <R> CompletableFuture<R> publish(InfrastructureEvent event) {
        return CompletableFuture.supplyAsync(() -> {
            Function<? super InfrastructureEvent, ?> handler = bus.get(event.getClass());
            if (handler != null) {
                return (R) handler.apply(event);
            } else {
                throw new IllegalArgumentException("no handler registered for " + event.getClass().getName());
            }
        });
    }

}
