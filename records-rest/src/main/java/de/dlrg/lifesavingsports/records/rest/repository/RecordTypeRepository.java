package de.dlrg.lifesavingsports.records.rest.repository;

import de.dlrg.lifesavingsports.records.api.RecordTypeDto;
import de.dlrg.lifesavingsports.records.rest.data.RecordType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecordTypeRepository extends JpaRepository<RecordType, String> {
    Optional<RecordTypeDto> findByAcronym(String acronym);
}
