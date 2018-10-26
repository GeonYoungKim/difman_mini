package org.depromeet.fill_day.controller;

import org.depromeet.fill_day.domain.dto.DayDTO;
import org.depromeet.fill_day.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/v1/days")
public class DayController {

    private final DayService dayService;

    @Autowired
    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DayDTO> create(@RequestBody DayDTO newDay) {
        return ResponseEntity.ok(dayService.create(newDay));
    }

    @GetMapping(value = "/{uid}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DayDTO> findByUID(@PathVariable String uid) {
        return dayService.findByUID(uid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DayDTO> findByDay(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return dayService.findByDay(date)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{uid}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DayDTO> update(@PathVariable String uid, @RequestBody DayDTO updatedDay) {
        return ResponseEntity.ok(dayService.update(uid, updatedDay));
    }
}
