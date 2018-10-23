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
    private Date dailyDate = new Date();

    private int score;

    private int toDo;

    private String evaluate;

    @ManyToOne
    private Report report;

    @OneToMany(mappedBy = "daily")
    private List<TimeLine> timeLines = new ArrayList<TimeLine>();

    @Builder
    public Daily(Date dailyDate, int score, int toDo, String evaluate, Report report) {
        this.dailyDate = dailyDate;
        this.score = score;
        this.toDo = toDo;
        this.evaluate = evaluate;
        this.report = report;
    }
}
