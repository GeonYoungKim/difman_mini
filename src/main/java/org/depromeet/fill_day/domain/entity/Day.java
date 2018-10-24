package org.depromeet.fill_day.domain.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Day {

    @Id
    @Type(type="uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uid;

    @Column
    private String title;

    @Column
    @Temporal(TemporalType.DATE)
    private Date today = new Date();

    @OneToMany(mappedBy = "day")
    private List<Timeline> timelines;

    @OneToMany(mappedBy = "day")
    private List<Todo> todoList;

    @ManyToOne
    @JoinColumn
    private Account account;

}
