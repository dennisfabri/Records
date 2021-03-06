package de.dlrg.lifesavingsports.records.service;

import de.dlrg.lifesavingsports.records.api.RecordTypeDto;

import java.util.Optional;

public interface RecordTypesRepository {
    void put(RecordTypeDto recordType);
    Optional<RecordTypeDto> findByAcronym(String acronym);
    RecordTypeDto[] findAll();
}
