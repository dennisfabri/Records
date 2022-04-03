package de.dlrg.lifesavingsports.records.api;

import de.dlrg.lifesavingsports.records.api.Gender;
import lombok.Value;

import java.time.Duration;
import java.time.LocalDate;

@Value
public class RecordDto {
    private final String acronym;
    private final String agegroup;
    private final Gender gender;
    private final String discipline;
    private final String name;
    private final String club;
    private final String nation;
    private final Duration time;
    private final String venue;
    private final LocalDate date;
}
