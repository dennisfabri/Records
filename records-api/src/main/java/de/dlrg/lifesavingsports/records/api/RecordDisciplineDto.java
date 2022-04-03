package de.dlrg.lifesavingsports.records.api;

import lombok.Value;

@Value
public class RecordDisciplineDto {
    private final String recordTypeAcronym;
    private final String agegroup;
    private final Gender gender;
    private final String discipline;
}
