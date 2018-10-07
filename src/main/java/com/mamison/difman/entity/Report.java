package com.mamison.difman.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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

    @Builder
    public Report(@NotNull String title, @NotNull String period, @NotNull Account account) {
        this.title = title;
        this.period = period;
        this.account = account;
    }
}
