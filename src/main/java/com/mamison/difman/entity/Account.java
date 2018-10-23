package com.mamison.difman.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    private String email;

    private String name;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Report> reports = new ArrayList<>();

    @Builder
    public Account(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public void addReport(Report report) {
        this.getReports().add(report);
        report.setAccount(this);
    }

    public void deleteReport(Report deleteReport) {
        this.getReports().remove(deleteReport);
        deleteReport.setAccount(null);
    }
}
