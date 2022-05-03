package de.dlrg.lifesavingsports.records.service;

import de.dlrg.lifesavingsports.records.api.RecordDisciplineDto;
import de.dlrg.lifesavingsports.records.api.RecordDto;
import de.dlrg.lifesavingsports.records.api.RecordTypeDto;
import de.dlrg.lifesavingsports.records.api.Gender;
import de.dlrg.lifesavingsports.records.service.exception.RecordTypeUnknownException;
import lombok.RequiredArgsConstructor;
import org.lisasp.basics.jre.id.IdGenerator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class RecordsService {

    private final RecordTypesRepository recordTypesRepository;
    private final RecordsRepository recordsRepository;
    private final IdGenerator idGenerator;

    public RecordTypeDto createRecordType(String name, String acronym) {
        RecordTypeDto dto = new RecordTypeDto(idGenerator.nextId(), name, acronym);
        recordTypesRepository.put(dto);
        return dto;
    }

    public RecordDisciplineDto createDiscipline(String recordTypeAcronym, String agegroup, Gender gender, String discipline) throws RecordTypeUnknownException {
        assertValidRecordTypeAcronym(recordTypeAcronym);
        return new RecordDisciplineDto(recordTypeAcronym, agegroup, gender, discipline);
    }

    public RecordTypeDto[] fetchRecordTypes() {
        return recordTypesRepository.findAll();
    }

    public RecordDto createRecord(RecordDisciplineDto recordDisciplineDto, String name, String club, String nation, Duration time, String venue, LocalDate date)
            throws RecordTypeUnknownException {
        assertValidRecordTypeAcronym(recordDisciplineDto.getRecordTypeAcronym());
        assertValidRecordDiscipline(recordDisciplineDto);
        return recordsRepository.save(new RecordDto(idGenerator.nextId(),
                                                    recordDisciplineDto.getRecordTypeAcronym(),
                                                    recordDisciplineDto.getAgegroup(),
                                                    recordDisciplineDto.getGender(),
                                                    recordDisciplineDto.getDiscipline(),
                                                    name,
                                                    club,
                                                    nation,
                                                    time,
                                                    venue,
                                                    date));
    }

    private void assertValidRecordDiscipline(RecordDisciplineDto recordDisciplineDto) {
    }

    private void assertValidRecordTypeAcronym(String acronym) throws RecordTypeUnknownException {
        recordTypesRepository.findByAcronym(acronym).orElseThrow(() -> new RecordTypeUnknownException(acronym));
    }

    public Stream<RecordDto> getRecords(String agegroup, Gender gender, int offset, int limit) {
        return recordsRepository.findByAgegroupAndGender(agegroup, gender).skip(offset).limit(limit);
    }
}
