package de.dlrg.lifesavingsports.records.rest.repository;

import de.dlrg.lifesavingsports.records.api.Gender;
import de.dlrg.lifesavingsports.records.rest.data.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<RecordEntity, String> {
    List<RecordEntity> findByAgegroupAndGender(String agegroup, Gender gender);
}
