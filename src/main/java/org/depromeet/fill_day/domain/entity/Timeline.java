package org.depromeet.fill_day.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date start;

    @Temporal(TemporalType.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date end;

    @Column(columnDefinition = "TEXT")
    private String plan;

    @Column(columnDefinition = "TEXT")
    private String retrospect;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private EvaluationScore score;

    public enum EvaluationScore {
        BEST(3),
        SOSO(2),
        BAD(1);

        private final int value;
        private EvaluationScore(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("timelines")
    private Day day;

}
