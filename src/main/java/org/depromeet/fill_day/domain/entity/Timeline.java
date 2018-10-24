package org.depromeet.fill_day.domain.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Timeline {

    @Id
    @Type(type="uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uid;

    @Temporal(TemporalType.TIME)
    private Date start;

    @Temporal(TemporalType.TIME)
    private Date end;

    @Column(columnDefinition = "TEXT")
    private String plan;

    @Column(columnDefinition = "TEXT")
    private String retrospect;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private EvaluationScore score;

    public enum EvaluationScore {
        BEST,
        SOSO,
        BAD
    }

    @ManyToOne
    @JoinColumn
    private Day day;

}
