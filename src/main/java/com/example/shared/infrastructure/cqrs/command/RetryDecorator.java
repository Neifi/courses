package com.example.shared.infrastructure.cqrs.command;

import com.example.shared.domain.exceptions.RetryException;
import com.example.shared.domain.exceptions.RetryThreadException;
import com.example.shared.infrastructure.cqrs.command.Command;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class RetryDecorator implements Function<Command, CompletableFuture<Void>> {
    private final Function<Command, CompletableFuture<Void>> delegate;
    private final int maxRetries;
    private final long initialDelay;
    private final double multiplier;
    private final long maxDelay;

    public RetryDecorator(Function<Command, CompletableFuture<Void>> delegate, int maxRetries, long initialDelay, double multiplier, long maxDelay) {
        this.delegate = delegate;
        this.maxRetries = maxRetries;
        this.initialDelay = initialDelay;
        this.multiplier = multiplier;
        this.maxDelay = maxDelay;
    }

    @Override
    public CompletableFuture<Void> apply(Command command) {
        return applyWithRetry(command, 0);
    }

    private CompletableFuture<Void> applyWithRetry(Command command, int retryCount) {
        try {
            return delegate.apply(command);
        } catch (RuntimeException e) {
            if (retryCount >= maxRetries) {
                throw new RetryException("max retry attempts exceeded");
            }
           
            long delay = Math.min((long) (initialDelay * Math.pow(multiplier, retryCount)), maxDelay);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                throw new RetryThreadException("retry was interrupted");
            }
            return applyWithRetry(command, retryCount + 1);
        }
    }
}
