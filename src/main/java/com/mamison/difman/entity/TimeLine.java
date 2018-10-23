package com.mamison.difman.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TimeLine {
    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIME)
    private Date startTime;

    @Temporal(TemporalType.TIME)
    private Date endTime;

    private String plan;

    private String record;

    private int score;

    @ManyToOne
    private Daily daily;

    @Builder
    public TimeLine(Date startTime, Date endTime, String plan, String record, int score, Daily daily) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.plan = plan;
        this.record = record;
        this.score = score;
        this.daily = daily;
    }
}
