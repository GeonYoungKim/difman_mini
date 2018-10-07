package com.mamison.difman.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Daily {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    private int score;

    @ManyToOne
    private Report report;

    @OneToMany(mappedBy = "daily")
    private List<DailyTime> dailyTimes = new ArrayList<DailyTime>();

    @Builder
    public Daily(Date date, int score, Report report) {
        this.date = date;
        this.score = score;
        this.report = report;
    }
}
