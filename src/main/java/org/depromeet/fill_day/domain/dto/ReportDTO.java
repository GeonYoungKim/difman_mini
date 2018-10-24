package org.depromeet.fill_day.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReportDTO {

    private Date from;

    private Date to;

    private long scoreAvg;

    @Data
    public class Achievement {

        private Date date;

        private long scoreAvg;

    }
}
