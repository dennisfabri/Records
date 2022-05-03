package de.dlrg.lifesavingsports.records.rest.data;

import de.dlrg.lifesavingsports.records.api.RecordTypeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lisasp.basics.spring.jpa.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RecordTypeEntity extends BaseEntity {

    @Column(length = 64, nullable = false, unique = true)
    private String name;

    @Column(length = 4, nullable = false, unique = true)
    private String acronym;

    public RecordTypeEntity(String id) {
        super(id);
    }

    public RecordTypeDto toDto() {
        return new RecordTypeDto(getId(), name, acronym);
    }
}
