package com.example.shared.infrastructure.cqrs.command;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface CommandHandler<R extends CompletableFuture<Void>, C extends Command> extends Function<C,R> {
}
