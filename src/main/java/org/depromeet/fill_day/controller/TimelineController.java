package org.depromeet.fill_day.controller;

import org.depromeet.fill_day.domain.dto.TimelineDTO;
import org.depromeet.fill_day.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/timelines")
public class TimelineController {

    private final TimelineService timelineService;

    @Autowired
    public TimelineController(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<TimelineDTO> create(@RequestBody TimelineDTO newTimeline) {
        return ResponseEntity.ok(timelineService.create(newTimeline));
    }

    @GetMapping(value = "/{uid}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<TimelineDTO> findByUID(@PathVariable String uid) {
        return timelineService.findByUID(uid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{uid}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<TimelineDTO> update(@PathVariable String uid, @RequestBody TimelineDTO updatedTimeline) {
        return ResponseEntity.ok(timelineService.update(uid, updatedTimeline));
    }

    @DeleteMapping(value = "/{uid}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity delete(@PathVariable String uid) {
        timelineService.delete(uid);
        return ResponseEntity.ok().build();
    }

}
