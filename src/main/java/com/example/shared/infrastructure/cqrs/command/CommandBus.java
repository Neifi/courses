package com.example.shared.infrastructure.cqrs.command;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface CommandBus {
    void registerCommandHandler(Class<? extends Command> commandClass, CommandHandler< CompletableFuture<Void>,Command> handler);

    CompletableFuture<Void> executeCommand(Command command);
}