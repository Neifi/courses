package com.example.shared.infrastructure.cqrs.query;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class InMemoryQueryBus implements QueryBus {
    private final Map<Class<? extends Query>, QueryHandler<?, ?>> handlers = new HashMap<>();

    @Override
    public <Q extends Query, R> void registerQueryHandler(Class<Q> queryClass, QueryHandler<Q, R> handler) {
        if (queryClass == null || handler == null) {
            throw new IllegalArgumentException("query class and handler must not be null");
        }
        QueryHandler<?, ?> existingHandler = handlers.putIfAbsent(queryClass, handler);
        if (existingHandler != null) {
            return;//TODO
        }

    }


    @Override
    public <R> CompletableFuture<R> executeQuery(Query query) {
        return CompletableFuture.supplyAsync(() -> {
            QueryHandler<Query, R> handler = (QueryHandler<Query, R>) handlers.get(query.getClass());
            if (handler != null) {
                return handler.apply(query);
            } else {
                throw new IllegalArgumentException("no handler registered for " + query.getClass().getName());
            }
        });
    }
}