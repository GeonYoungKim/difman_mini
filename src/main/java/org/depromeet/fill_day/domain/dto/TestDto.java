package org.depromeet.fill_day.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestDto {

    private String a1;
    private String a2;

    @Builder
    public TestDto(String a1, String a2) {
        this.a1 = a1;
        this.a2 = a2;
    }
}
