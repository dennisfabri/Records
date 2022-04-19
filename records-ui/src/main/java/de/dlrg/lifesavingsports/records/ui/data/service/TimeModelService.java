package de.dlrg.lifesavingsports.records.ui.data.service;

import de.dlrg.lifesavingsports.records.ui.data.entity.TimeModel;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class TimeModelService {

    public TimeModelService() {

    }

    public Optional<TimeModel> get(UUID id) {
        return Optional.of(new TimeModel());
    }

    public TimeModel update(TimeModel entity) {
        return entity;
    }

    public void delete(UUID id) {}

    public List<TimeModel> list() {
        return Arrays.asList(new TimeModel(), new TimeModel());
    }

    public int count() {
        return 2;
    }

    public Stream<TimeModel> findAll() {
        return list().stream();
    }
}
