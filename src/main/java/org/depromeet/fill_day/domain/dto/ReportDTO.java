package org.depromeet.fill_day.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Data
@Builder
public class ReportDTO {

    private Date from;

    private Date to;

    private double scoreAvg;

    private Collection<Achievement> achievements;

    @Data
    @Builder
    public static class Achievement {

        private Date date;

        private double scoreAvg;

    }
}
