package de.dlrg.lifesavingsports.records.test.service.doubles;

import de.dlrg.lifesavingsports.records.api.RecordTypeDto;
import de.dlrg.lifesavingsports.records.service.RecordTypeDtoRepository;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class InMemoryRecordTypeDtoRepository implements RecordTypeDtoRepository {

    private final Set<RecordTypeDto> entries = new HashSet<>();

    @Override
    public void put(@NonNull RecordTypeDto recordType) {
        entries.add(recordType);
    }

    @Override
    public Optional<RecordTypeDto> findByAcronym(@NonNull String acronym) {
        return entries.stream().filter(dto -> dto.getAcronym().equals(acronym)).findFirst();
    }

    @Override
    public RecordTypeDto[] findAll() {
        return entries.toArray(RecordTypeDto[]::new);
    }
}
