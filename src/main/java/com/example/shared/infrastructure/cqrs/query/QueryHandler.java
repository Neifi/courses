package com.example.shared.infrastructure.cqrs.query;

import java.util.function.Function;
import java.util.logging.Handler;

public interface QueryHandler<Q extends Query, R> extends Function<Q, R> {

}
