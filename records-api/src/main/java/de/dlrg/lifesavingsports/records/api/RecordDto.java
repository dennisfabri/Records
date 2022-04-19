package de.dlrg.lifesavingsports.records.api;

import de.dlrg.lifesavingsports.records.api.Gender;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.Duration;
import java.time.LocalDate;

@Data
public class RecordDto {
    private  String acronym;
    private  String agegroup;
    private  Gender gender;
    private  String discipline;
    private  String name;
    private  String club;
    private  String nation;
    private  Duration time;
    private  String venue;
    private  LocalDate date;
}
