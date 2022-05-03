package de.dlrg.lifesavingsports.records.rest.data;

import de.dlrg.lifesavingsports.records.api.Gender;
import de.dlrg.lifesavingsports.records.api.RecordDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lisasp.basics.spring.jpa.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import java.time.Duration;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RecordEntity extends BaseEntity {

    @Column(length = 4, nullable = false)
    private String acronym;
    @Column(length = 64, nullable = false)
    private String agegroup;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(length = 64, nullable = false)
    private String discipline;
    @Column(length = 64, nullable = false)
    private String name;
    @Column(length = 64, nullable = false)
    private String club;
    @Column(length = 64, nullable = false)
    private String nation;
    @Column(nullable = false)
    @Min(0)
    private int timeInMillis;
    @Column(length = 64, nullable = false)
    private String venue;
    @Column(nullable = false)
    private LocalDate date;

    public RecordEntity(String id) {
        super(id);
    }

    public static RecordEntity fromDto(RecordDto recordDto) {
        RecordEntity entity = new RecordEntity(recordDto.getId());
        entity.acronym = recordDto.getAcronym();
        entity.agegroup = recordDto.getAgegroup();
        entity.gender = recordDto.getGender();
        entity.discipline = recordDto.getDiscipline();
        entity.name = recordDto.getName();
        entity.club = recordDto.getClub();
        entity.nation = recordDto.getNation();
        entity.timeInMillis = (int) recordDto.getTime().toMillis();
        entity.venue = recordDto.getVenue();
        entity.date = recordDto.getDate();
        return entity;
    }

    public RecordDto toDto() {
        return new RecordDto(getId(), acronym, agegroup, gender, discipline, name, club, nation, Duration.ofMillis(timeInMillis), venue, date);
    }
}
