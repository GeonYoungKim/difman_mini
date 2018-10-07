package com.mamison.difman.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Report implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String title;
    @NotNull
    private String period;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<Daily> dailies = new ArrayList<Daily>();

    @Builder
    public Report(@NotNull String title, @NotNull String period, @NotNull Account account) {
        this.title = title;
        this.period = period;
        this.account = account;
    }
}
