package org.depromeet.fill_day.domain.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Account implements Serializable {

    @Id
    @Type(type="uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uid;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String accessToken;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Provider provider;

    @OneToMany(mappedBy="account", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Day> days;

    public enum Provider {
        KAKAO,
        NAVER
    }
}
