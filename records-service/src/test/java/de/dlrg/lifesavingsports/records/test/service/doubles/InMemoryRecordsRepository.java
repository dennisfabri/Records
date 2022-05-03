package de.dlrg.lifesavingsports.records.test.service.doubles;

import de.dlrg.lifesavingsports.records.api.Gender;
import de.dlrg.lifesavingsports.records.api.RecordDto;
import de.dlrg.lifesavingsports.records.service.RecordsRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Stream;

public class InMemoryRecordsRepository implements RecordsRepository {

    private final Map<String, RecordDto> entries = new HashMap<>();

    @Override
    public Stream<RecordDto> findByAgegroupAndGender(String agegroup, Gender gender) {
        return entries.values().stream()
                .filter(record -> record.getAgegroup().equals(agegroup) && record.getGender().equals(gender))
                .sorted(Comparator.comparing(RecordDto::getId));
    }

    @Override
    public RecordDto save(RecordDto recordDto) {
        entries.put(recordDto.getId(), recordDto);
        return recordDto;
    }
}
