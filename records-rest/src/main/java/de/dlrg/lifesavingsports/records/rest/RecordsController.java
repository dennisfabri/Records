package de.dlrg.lifesavingsports.records.rest;

import de.dlrg.lifesavingsports.records.api.Gender;
import de.dlrg.lifesavingsports.records.api.RecordDto;
import de.dlrg.lifesavingsports.records.service.RecordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class RecordsController {

    private final RecordsService recordsService;

    @GetMapping("/records")
    public RecordDto[] getRecords(@RequestParam String agegroup, @RequestParam String gender, @RequestParam int offset, @RequestParam int limit) {
        return recordsService.getRecords(agegroup, Gender.valueOf(gender), offset, limit).toArray(RecordDto[]::new);
    }
}
