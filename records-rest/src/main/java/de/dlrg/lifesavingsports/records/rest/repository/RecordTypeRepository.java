package de.dlrg.lifesavingsports.records.rest.repository;

import de.dlrg.lifesavingsports.records.api.RecordTypeDto;
import de.dlrg.lifesavingsports.records.rest.data.RecordTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecordTypeRepository extends JpaRepository<RecordTypeEntity, String> {
    Optional<RecordTypeEntity> findByAcronym(String acronym);
}
