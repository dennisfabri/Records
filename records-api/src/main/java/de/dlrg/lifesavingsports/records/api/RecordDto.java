package de.dlrg.lifesavingsports.records.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.Duration;
import java.time.LocalDate;

@Value
public class RecordDto {
    private String id;
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

    @JsonCreator
    public static RecordDto create(@JsonProperty("id") String id,
                                   @JsonProperty("acronym") String acronym,
                                   @JsonProperty("agegroup") String agegroup,
                                   @JsonProperty("gender") Gender gender,
                                   @JsonProperty("discipline") String discipline,
                                   @JsonProperty("name") String name,
                                   @JsonProperty("club") String club,
                                   @JsonProperty("nation") String nation,
                                   @JsonProperty("time") Duration time,
                                   @JsonProperty("venue") String venue,
                                   @JsonProperty("date") LocalDate date) {
        return new RecordDto(id, acronym, agegroup, gender, discipline, name, club, nation, time, venue, date);
    }
}
