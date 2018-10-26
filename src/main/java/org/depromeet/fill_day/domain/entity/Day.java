package org.depromeet.fill_day.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@EqualsAndHashCode(exclude={"timelines", "todoList"})
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

    @OneToMany(mappedBy = "day", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("day")
    private Set<Timeline> timelines;

    @OneToMany(mappedBy = "day", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("day")
    private Set<Todo> todoList;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("days")
    private Account account;

}
