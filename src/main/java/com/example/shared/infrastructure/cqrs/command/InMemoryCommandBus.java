package com.example.shared.infrastructure.cqrs.command;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class InMemoryCommandBus implements CommandBus {

    private final Map<Class<? extends Command>, CommandHandler< CompletableFuture<Void>,Command>> handlers = new HashMap<>();


    @Override
    public void registerCommandHandler(Class<? extends Command> commandClass, CommandHandler<CompletableFuture<Void>, Command> handler) {
        handlers.put(commandClass, handler);

    }

    @Override
    public CompletableFuture<Void> executeCommand(Command command) {
        Function<Command, CompletableFuture<Void>> handler = handlers.get(command.getClass());
        if (handler != null) {
            return handler.apply(command);
        } else {
            return CompletableFuture.failedFuture(new IllegalArgumentException("no handler registered for " + command.getClass().getName()));
        }
    }
}
