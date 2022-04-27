package de.dlrg.lifesavingsports.records.test.service;

import de.dlrg.lifesavingsports.records.api.Gender;
import de.dlrg.lifesavingsports.records.api.RecordDisciplineDto;
import de.dlrg.lifesavingsports.records.api.RecordDto;
import de.dlrg.lifesavingsports.records.api.RecordTypeDto;
import de.dlrg.lifesavingsports.records.service.RecordsService;
import de.dlrg.lifesavingsports.records.service.exception.RecordTypeUnknownException;
import de.dlrg.lifesavingsports.records.test.service.doubles.InMemoryRecordTypesRepository;
import de.dlrg.lifesavingsports.records.test.service.doubles.InMemoryRecordsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.lisasp.basics.jre.id.CountingIdGenerator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RecordsServiceTests {

    private RecordsService recordsService;

    @Nested
    class RecordTypeTests {

        @BeforeEach
        void prepare() {
            recordsService = new RecordsService(new InMemoryRecordTypesRepository(), new InMemoryRecordsRepository(), new CountingIdGenerator());
        }

        @Test
        void createRecordType() {
            RecordTypeDto recordTypeDto = recordsService.createRecordType("Deutscher Rekord", "DR");

            assertEquals("Deutscher Rekord", recordTypeDto.getName());
            assertEquals("DR", recordTypeDto.getAcronym());
        }

        @Test
        void fetchRecordTypesWithOneEntry() {
            recordsService.createRecordType("Deutscher Rekord 1", "DR1");
            RecordTypeDto[] expected = new RecordTypeDto[]{
                    new RecordTypeDto("1", "Deutscher Rekord 1", "DR1")
            };

            RecordTypeDto[] actual = recordsService.fetchRecordTypes();

            RecordTypeDto[] sortedActual = Arrays.stream(actual).sorted(Comparator.comparing(RecordTypeDto::getAcronym)).toArray(RecordTypeDto[]::new);
            assertArrayEquals(expected, sortedActual);
        }

        @Test
        void fetchRecordTypesWithTwoEntries() {
            recordsService.createRecordType("Deutscher Rekord 1", "DR1");
            recordsService.createRecordType("Deutscher Rekord 2", "DR2");
            RecordTypeDto[] expected = new RecordTypeDto[]{
                    new RecordTypeDto("1", "Deutscher Rekord 1", "DR1"),
                    new RecordTypeDto("2", "Deutscher Rekord 2", "DR2")
            };

            RecordTypeDto[] actual = recordsService.fetchRecordTypes();

            assertArrayEquals(expected, Arrays.stream(actual).sorted(Comparator.comparing(RecordTypeDto::getAcronym)).toArray());
        }

        @Test
        void fetchRecordTypesWithThreeEntries() {
            recordsService.createRecordType("Deutscher Rekord 1", "DR1");
            recordsService.createRecordType("Deutscher Rekord 2", "DR2");
            recordsService.createRecordType("Deutscher Rekord 3", "DR3");
            RecordTypeDto[] expected = new RecordTypeDto[]{
                    new RecordTypeDto("1", "Deutscher Rekord 1", "DR1"),
                    new RecordTypeDto("2", "Deutscher Rekord 2", "DR2"),
                    new RecordTypeDto("3", "Deutscher Rekord 3", "DR3")
            };

            RecordTypeDto[] actual = recordsService.fetchRecordTypes();

            assertArrayEquals(expected, Arrays.stream(actual).sorted(Comparator.comparing(RecordTypeDto::getAcronym)).toArray());
        }
    }

    @Nested
    class RecordDisciplineTests {

        @BeforeEach
        void prepare() {
            recordsService = new RecordsService(new InMemoryRecordTypesRepository(), new InMemoryRecordsRepository(), new CountingIdGenerator());
            recordsService.createRecordType("Deutscher Rekord", "DR");
        }

        @Test
        void createDiscipline() {
            RecordDisciplineDto recordDisciplineDto = recordsService.createDiscipline("DR", "Altersklasse", Gender.Mixed, "Disziplin");

            assertEquals("DR", recordDisciplineDto.getRecordTypeAcronym());
            assertEquals("Altersklasse", recordDisciplineDto.getAgegroup());
            assertEquals(Gender.Mixed, recordDisciplineDto.getGender());
            assertEquals("Disziplin", recordDisciplineDto.getDiscipline());
        }

        @Test
        void createDisciplineWithMissingRecordType() {
            assertThrows(RecordTypeUnknownException.class, () -> recordsService.createDiscipline("??", "Altersklasse", Gender.Mixed, "Disziplin"));
        }
    }

    @Nested
    class RecordTests {

        private RecordDisciplineDto recordDisciplineDto;

        @BeforeEach
        void prepare() {
            recordsService = new RecordsService(new InMemoryRecordTypesRepository(), new InMemoryRecordsRepository(), new CountingIdGenerator());
            recordsService.createRecordType("Deutscher Rekord", "DR");
            recordDisciplineDto = recordsService.createDiscipline("DR", "Altersklasse", Gender.Mixed, "Disziplin");
        }

        @Test
        void createRecord() {
            Duration time = Duration.ofMillis(123450);
            LocalDate date = LocalDate.of(2022, Month.APRIL, 2);

            RecordDto record = recordsService.createRecord(recordDisciplineDto, "Name", "Club", "Nation", time, "Venue", date);

            assertEquals("DR", record.getAcronym());
            assertEquals("Altersklasse", record.getAgegroup());
            assertEquals(Gender.Mixed, record.getGender());
            assertEquals("Disziplin", record.getDiscipline());
            assertEquals("Name", record.getName());
            assertEquals("Club", record.getClub());
            assertEquals("Nation", record.getNation());
            assertEquals(time, record.getTime());
            assertEquals("Venue", record.getVenue());
            assertEquals(date, record.getDate());
        }

        @Test
        void createRecordWithInvalidRecordType() {
            recordDisciplineDto = new RecordDisciplineDto("??", "Altersklasse", Gender.Mixed, "Disziplin");
            Duration time = Duration.ofMillis(123450);
            LocalDate date = LocalDate.of(2022, Month.APRIL, 2);

            assertThrows(RecordTypeUnknownException.class,
                    () -> recordsService.createRecord(recordDisciplineDto, "Name", "Club", "Nation", time, "Venue", date));
        }

        @Test
        void getRecordsWithEmptyResult() {
            RecordDto[] expected = new RecordDto[0];

            RecordDto[] actual = recordsService.getRecords("Altersklasse", Gender.Mixed, 0, 50);

            assertArrayEquals(expected, actual);
        }

        @Test
        void getRecordsWithOneEntry() {
            Duration time = Duration.ofMillis(123450);
            LocalDate date = LocalDate.of(2022, Month.APRIL, 2);
            RecordDto recordDto = recordsService.createRecord(recordDisciplineDto, "Name", "Club", "Nation", time, "Venue", date);
            RecordDto[] expected = new RecordDto[]{recordDto};

            RecordDto[] actual = recordsService.getRecords(recordDisciplineDto.getAgegroup(), recordDisciplineDto.getGender(), 0, 50);

            assertArrayEquals(expected, actual);
        }
    }

}
