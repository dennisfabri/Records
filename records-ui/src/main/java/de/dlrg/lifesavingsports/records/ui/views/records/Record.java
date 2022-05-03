package de.dlrg.lifesavingsports.records.ui.views.records;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Value
public class Record {
    private String discipline;
    private String name;
    private String club;
    private String nation;
    private String gender;
    private String venue;
    private String date;
    private String time;
}
