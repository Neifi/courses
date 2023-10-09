package com.example.shared.infrastructure.cqrs.query;


import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface QueryBus {
    <Q extends Query,R> void registerQueryHandler(Class<Q> queryClass, QueryHandler<Q, R> handler);
    <R> CompletableFuture<R> executeQuery(Query query);
}