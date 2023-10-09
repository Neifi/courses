package com.example.courses.infrastructure.persistence.sql.exceptions;

public class DomainEventAlreadyPersisted extends RuntimeException{
    public DomainEventAlreadyPersisted() {
    }

    public DomainEventAlreadyPersisted(String message) {
        super(message);
    }
}
