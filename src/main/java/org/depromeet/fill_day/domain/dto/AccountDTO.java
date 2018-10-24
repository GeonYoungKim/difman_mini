package org.depromeet.fill_day.domain.dto;

import lombok.*;

@Data
@Builder
public class AccountDTO {

    private String uid;
    private String email;
    private String name;
    private String accessToken;
    private String provider;

}
