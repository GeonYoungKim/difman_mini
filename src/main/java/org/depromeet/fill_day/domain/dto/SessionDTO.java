package org.depromeet.fill_day.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class SessionDTO {

    private String uid;

    private String accessToken;

}
