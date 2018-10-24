package org.depromeet.fill_day.domain.dto;


import lombok.Data;

import java.util.List;

@Data
public class DayDTO {

    private String uid;

    private String title;

    private String today;

    private List<TimelineDTO> timelines;

    private List<TodoDTO> todoList;

}
