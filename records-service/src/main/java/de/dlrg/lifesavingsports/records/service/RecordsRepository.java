package de.dlrg.lifesavingsports.records.service;

import de.dlrg.lifesavingsports.records.api.Gender;
import de.dlrg.lifesavingsports.records.api.RecordDto;

public interface RecordsRepository {
    RecordDto[] findByAgegroupAndGender(String agegroup, Gender gender);
}
