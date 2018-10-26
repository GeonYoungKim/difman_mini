package org.depromeet.fill_day.controller;

import org.depromeet.fill_day.domain.dto.ReportDTO;
import org.depromeet.fill_day.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/v1/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<ReportDTO> calculateByDate(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date from,
                                                     @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date to) {
        return ResponseEntity.ok(reportService.calculateByDate(from, to));
    }

}
