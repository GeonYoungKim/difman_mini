package org.depromeet.fill_day.domain.dto;

import lombok.Data;

@Data
public class ErrorDTO {

    private String message;

    public ErrorDTO(String message) {
        this.message = message;
    }

}
