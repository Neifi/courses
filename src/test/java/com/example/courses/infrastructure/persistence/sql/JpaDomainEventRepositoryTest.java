package com.example.courses.infrastructure.persistence.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@SpringBootTest
//@Testcontainers
class JpaDomainEventRepositoryTest {
    @Container
    public static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private JpaDomainEventRepository domainEventRepository;

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    public void givenExistingEvents_whenSavingConcurrentEvents_itShouldThrowOptimisticLockingFailureException() {
        String aggregateId = UUID.randomUUID().toString();
        DomainEventEntity event1 = new DomainEventEntity(aggregateId, "type", "{}", Instant.now(), 1);
        domainEventRepository.saveDomainEvent(event1);

        DomainEventEntity event2 = new DomainEventEntity(aggregateId, "type", "{}", Instant.now(), 2);
        domainEventRepository.saveDomainEvent(event2);

        DomainEventEntity concurrentEvent = new DomainEventEntity(aggregateId, "type", "{}", Instant.now(), 2);

        assertThrows(OptimisticLockingFailureException.class, () -> {
            domainEventRepository.saveDomainEvent(concurrentEvent);
        });
    }

    public void givenExistingEvents_whenNoConcurrencyOccurs_itShouldBeSaved() {
        String aggregateId = UUID.randomUUID().toString();
        DomainEventEntity event1 = new DomainEventEntity(aggregateId, "type", "{}", Instant.now(), 1);
        domainEventRepository.saveDomainEvent(event1);

        DomainEventEntity event2 = new DomainEventEntity(aggregateId, "type", "{}", Instant.now(), 2);
        domainEventRepository.saveDomainEvent(event2);

        DomainEventEntity concurrentEvent = new DomainEventEntity(aggregateId, "type", "{}", Instant.now(), 3);

        assertDoesNotThrow(() -> {
            domainEventRepository.saveDomainEvent(concurrentEvent);
        });
    }

    public void givenNoPreviousEvents_whenNoConcurrencyOccurs_itShouldBeSaved() {
        String aggregateId = UUID.randomUUID().toString();

        DomainEventEntity concurrentEvent = new DomainEventEntity(aggregateId, "type", "{}", Instant.now(), 1);
        assertDoesNotThrow(() -> {
            domainEventRepository.saveDomainEvent(concurrentEvent);
        });
    }
}