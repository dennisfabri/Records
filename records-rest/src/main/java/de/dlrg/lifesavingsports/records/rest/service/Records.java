package de.dlrg.lifesavingsports.records.rest.service;

import de.dlrg.lifesavingsports.records.api.Gender;
import de.dlrg.lifesavingsports.records.api.RecordDto;
import de.dlrg.lifesavingsports.records.rest.data.RecordEntity;
import de.dlrg.lifesavingsports.records.rest.repository.RecordRepository;
import de.dlrg.lifesavingsports.records.service.RecordsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Slf4j
@Service
public class Records implements RecordsRepository {

    private final RecordRepository repository;

    @Override
    public Stream<RecordDto> findByAgegroupAndGender(String agegroup, Gender gender) {
        return repository.findByAgegroupAndGender(agegroup, gender).stream().map(rekord -> rekord.toDto());
    }

    @Override
    public RecordDto save(RecordDto recordDto) {
        return repository.save(RecordEntity.fromDto(recordDto)).toDto();
    }
}
