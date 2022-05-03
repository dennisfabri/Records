package de.dlrg.lifesavingsports.records.service.imports;

import com.opencsv.bean.CsvBindByName;
import de.dlrg.lifesavingsports.records.api.Gender;
import de.dlrg.lifesavingsports.records.api.RecordDto;
import lombok.Data;
import lombok.Value;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

@Data
public class ImportedRecord {

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @CsvBindByName(column = "acronym")
    private String acronym;
    @CsvBindByName(column = "agegroup")
    private String agegroup;
    @CsvBindByName(column = "gender")
    private Gender gender;
    @CsvBindByName(column = "discipline")
    private String discipline;
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "club")
    private String club;
    @CsvBindByName(column = "nation")
    private String nation;
    @CsvBindByName(column = "time")
    private int timeInMillis;
    @CsvBindByName(column = "venue")
    private String venue;
    @CsvBindByName(column = "date")
    private String date;

    public RecordDto toDto(String id) {
        return new RecordDto(id, fixAcronym(acronym), agegroup, gender, discipline, name, club, nation, Duration.ofMillis(timeInMillis), venue, LocalDate.parse(date, formatter));
    }

    private String fixAcronym(String acronym) {
        if (acronym == null || acronym.length() == 0) {
            acronym = "DR";
        }
        return acronym;
    }
}
