package de.dlrg.lifesavingsports.records.rest.data;

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
public class RecordType extends BaseEntity {

    @Column(length = 64, nullable = false, unique = true)
    private String name;

    @Column(length = 4, nullable = false, unique = true)
    private String acronym;

    public RecordType(String id) {
        super(id);
    }
}
