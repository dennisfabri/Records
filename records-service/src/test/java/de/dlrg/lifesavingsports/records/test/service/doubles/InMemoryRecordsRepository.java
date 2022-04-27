package de.dlrg.lifesavingsports.records.test.service.doubles;

import de.dlrg.lifesavingsports.records.api.Gender;
import de.dlrg.lifesavingsports.records.api.RecordDto;
import de.dlrg.lifesavingsports.records.service.RecordsRepository;

import java.util.HashSet;
import java.util.Set;

public class InMemoryRecordsRepository implements RecordsRepository {

    private final Set<RecordDto> entries = new HashSet<>();

    @Override
    public RecordDto[] findByAgegroupAndGender(String agegroup, Gender gender) {
        return entries.stream().filter(record -> record.getAgegroup().equals(agegroup) && record.getGender().equals(gender)).toArray(RecordDto[]::new);
    }
}
