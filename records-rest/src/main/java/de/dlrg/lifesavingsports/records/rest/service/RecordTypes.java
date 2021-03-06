package de.dlrg.lifesavingsports.records.rest.service;

import de.dlrg.lifesavingsports.records.api.RecordTypeDto;
import de.dlrg.lifesavingsports.records.rest.data.RecordTypeEntity;
import de.dlrg.lifesavingsports.records.rest.repository.RecordTypeRepository;
import de.dlrg.lifesavingsports.records.service.RecordTypesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecordTypes implements RecordTypesRepository {

    private final RecordTypeRepository repository;

    @Override
    public void put(RecordTypeDto recordType) {
        RecordTypeEntity entity = repository.findById(recordType.getId()).orElseGet(() -> new RecordTypeEntity(recordType.getId()));
        entity.setAcronym(recordType.getAcronym());
        entity.setName(recordType.getName());
        repository.save(entity);
    }

    @Override
    public Optional<RecordTypeDto> findByAcronym(String acronym) {
        return repository.findByAcronym(acronym).map(recordType -> recordType.toDto());
    }

    @Override
    public RecordTypeDto[] findAll() {
        return repository.findAll().stream().map(RecordTypes::toDto).toArray(RecordTypeDto[]::new);
    }

    private static RecordTypeDto toDto(RecordTypeEntity recordType) {
        return new RecordTypeDto(recordType.getId(), recordType.getName(), recordType.getAcronym());
    }
}
