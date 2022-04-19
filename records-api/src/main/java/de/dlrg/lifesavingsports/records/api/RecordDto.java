package de.dlrg.lifesavingsports.records.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDto {
    private String acronym;
    private String agegroup;
    private Gender gender;
    private String discipline;
    private String name;
    private String club;
    private String nation;
    private Duration time;
    private String venue;
    private LocalDate date;
}
