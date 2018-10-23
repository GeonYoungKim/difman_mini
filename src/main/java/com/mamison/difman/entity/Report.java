package com.mamison.difman.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Report implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date startDate = new Date();

    @Temporal(TemporalType.DATE)
    private Date endDate = new Date();

    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<Daily> dailies = new ArrayList<Daily>();

    @Builder
    public Report(Account account,Date startDate, Date endDate, String title) {
        this.account = account;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
    }
}
