package de.dlrg.lifesavingsports.records.rest;

import de.dlrg.lifesavingsports.records.api.RecordDto;
import de.dlrg.lifesavingsports.records.service.RecordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController()
@RequestMapping("api")
public class RecordsController {

    private final RecordsService recordsService;

    @GetMapping("/records")
    public RecordDto[] getRecords() {
        return recordsService.getRecords();
    }

    @PutMapping("/records/{id}")
    public String put(@RequestParam String id, @RequestBody RecordDto record) {
        return "Admin page";
    }

    @PutMapping("/records/{id}/approve")
    public String approve() {
        return "Admin page";
    }
}
