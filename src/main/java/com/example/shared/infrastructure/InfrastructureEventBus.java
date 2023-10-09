package com.example.shared.infrastructure;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface InfrastructureEventBus {
    <R> CompletableFuture<R> publish(InfrastructureEvent event);
    <R> void subscribe(Class<? extends InfrastructureEvent> queryClass, Function<? super InfrastructureEvent, R> handler);
}
