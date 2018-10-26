package org.depromeet.fill_day.domain.dto;

import lombok.Data;

@Data
public class TodoDTO {

    private String uid;

    private String summary;

    private boolean isCompleted;

    private DayDTO day;

}
