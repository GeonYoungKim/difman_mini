package org.depromeet.fill_day.domain.dto;


import lombok.Data;
import org.depromeet.fill_day.domain.entity.Timeline;

@Data
public class TimelineDTO {

    private String uid;

    private String start;

    private String end;

    private String plan;

    private String retrospect;

    private Timeline.EvaluationScore score;

    private DayDTO day;

}
