package com.mamison.difman.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DailyTime {

    @Id
    @GeneratedValue
    private Long id;

    private String time;

    private String expect;

    private String really;

    private int score;

    @ManyToOne
    private Daily daily;

    @Builder
    public DailyTime(String time, String expect, String really, int score) {
        this.time = time;
        this.expect = expect;
        this.really = really;
        this.score = score;
    }
}
