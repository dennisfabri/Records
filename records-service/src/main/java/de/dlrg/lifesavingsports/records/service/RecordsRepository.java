package de.dlrg.lifesavingsports.records.service;

import de.dlrg.lifesavingsports.records.api.Gender;
import de.dlrg.lifesavingsports.records.api.RecordDto;

import java.util.stream.Stream;

public interface RecordsRepository {
    Stream<RecordDto> findByAgegroupAndGender(String agegroup, Gender gender);

    RecordDto save(RecordDto recordDto);
}
