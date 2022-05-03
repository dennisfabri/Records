package de.dlrg.lifesavingsports.records.test.service;

import de.dlrg.lifesavingsports.records.api.Gender;
import de.dlrg.lifesavingsports.records.api.RecordDto;
import de.dlrg.lifesavingsports.records.service.imports.RecordsImporter;
import de.dlrg.lifesavingsports.records.test.service.doubles.InMemoryRecordTypesRepository;
import de.dlrg.lifesavingsports.records.test.service.doubles.InMemoryRecordsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lisasp.basics.jre.id.CountingIdGenerator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RecordsImporterTests {

    private RecordsImporter recordsImporter;
    private InMemoryRecordsRepository recordsRepository;

    private RecordDto[] females = new RecordDto[] {
            new RecordDto("1", "DR", "AK 12", Gender.Female, "4 x 50 m Hindernisstaffel", "Team 1", "Club 1", "DE", Duration.ofMillis(155820), "V1", LocalDate.of(2022,
                                                                                                                                                                 Month.JANUARY, 1)),
            new RecordDto("2", "DR", "AK 12", Gender.Female, "4 x 25 m Rettungsstaffel", "Team 2", "Club 2", "DE", Duration.ofMillis(58170), "V2", LocalDate.of(2022,
                                                                                                                                                                 Month.JANUARY, 1)),
            new RecordDto("3", "DR", "AK 12", Gender.Female, "4 x 25 m Gurtretterstaffel", "Team 3", "Club 3", "DE", Duration.ofMillis(71330), "V3", LocalDate.of(2022,
                                                                                                                                                                 Month.JANUARY, 1)),
            new RecordDto("4", "DR", "AK 12", Gender.Female, "4 x 25 m Rückenlage ohne Armtätigkeit", "Team 4", "Club 4", "DE", Duration.ofMillis(82190), "V4", LocalDate.of(2022,
                                                                                                                                                                 Month.JANUARY, 1))
    };

    private RecordDto[] males = new RecordDto[] {
            new RecordDto("5", "DR", "AK 12", Gender.Male, "4 x 50 m Hindernisstaffel", "Team 5", "Club 5", "DE", Duration.ofMillis(155810), "V5", LocalDate.of(2022,
                                                                                                                                                                  Month.JANUARY, 1)),
            new RecordDto("6", "DR", "AK 12", Gender.Male, "4 x 25 m Rettungsstaffel", "Team 6", "Club 6", "DE", Duration.ofMillis(56740), "V6", LocalDate.of(2022,
                                                                                                                                                                Month.JANUARY, 1)),
            new RecordDto("7", "DR", "AK 12", Gender.Male, "4 x 25 m Gurtretterstaffel", "Team 7", "Club 7", "DE", Duration.ofMillis(71130), "V7", LocalDate.of(2022,
                                                                                                                                                                  Month.JANUARY, 1)),
            new RecordDto("8", "DR", "AK 12", Gender.Male, "4 x 25 m Rückenlage ohne Armtätigkeit", "Team 8", "Club 8", "DE", Duration.ofMillis(80260), "V8", LocalDate.of(2022,
                                                                                                                                                                             Month.JANUARY, 1))
    };

    @BeforeEach
    void prepare() {
        recordsRepository = new InMemoryRecordsRepository();
        recordsImporter = new RecordsImporter(new InMemoryRecordTypesRepository(), recordsRepository, new CountingIdGenerator());
    }

    @Test
    void importRecordsTeam() {
        recordsImporter.importCSV(Thread.currentThread().getContextClassLoader().getResourceAsStream("records-team.csv"));

        assertArrayEquals(females, recordsRepository.findByAgegroupAndGender("AK 12", Gender.Female).toArray(RecordDto[]::new));
        assertArrayEquals(males, recordsRepository.findByAgegroupAndGender("AK 12", Gender.Male).toArray(RecordDto[]::new));
    }
}
