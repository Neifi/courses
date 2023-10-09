package com.example.courses.infrastructure.persistence.sql;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaDomainEventRepository extends JpaRepository<DomainEventEntity, String> {

    default List<DomainEventEntity> findAllByAggregateId(String id) {
        return findAllByAggregateIdOrderByVersion(id);
    }

    List<DomainEventEntity> findAllByAggregateIdOrderByVersion(String id);

    @Transactional
    default void saveDomainEvent(DomainEventEntity domainEventEntity) {
        int currentVersion = domainEventEntity.getVersion();
        String aggregateId = domainEventEntity.getAggregateId();

        Optional<Integer> lastVersionOpt = findLastVersionByAggregateId(aggregateId);

        if (lastVersionOpt.isPresent()) {
            int lastVersion = lastVersionOpt.get();
            if (currentVersion - 1 != lastVersion) {
                throw new OptimisticLockingFailureException("concurrent modification detected for aggregate ID " +
                        aggregateId + ": expected version " + (lastVersion + 1) + " but got " + currentVersion);
            }
        }

        save(domainEventEntity);
    }


    @Query("SELECT MAX(e.version) FROM DomainEventEntity e WHERE e.aggregateId = :aggregateId")
    Optional<Integer> findLastVersionByAggregateId(@Param("aggregateId") String aggregateId);


}
