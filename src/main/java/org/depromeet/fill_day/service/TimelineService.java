package org.depromeet.fill_day.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.depromeet.fill_day.domain.dto.TimelineDTO;
import org.depromeet.fill_day.domain.entity.Timeline;
import org.depromeet.fill_day.repository.DayRepository;
import org.depromeet.fill_day.repository.TimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TimelineService {

    private TimelineRepository timelineRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public TimelineService(TimelineRepository timelineRepository,
                           ObjectMapper objectMapper) {
        this.timelineRepository = timelineRepository;
        this.objectMapper = objectMapper;
    }

    public TimelineDTO create(TimelineDTO newTimelineDTO) {
        Timeline newTimeline = objectMapper.convertValue(newTimelineDTO, Timeline.class);
        Timeline createdTimeline = timelineRepository.save(newTimeline);

        return objectMapper.convertValue(createdTimeline, TimelineDTO.class);
    }

    public Optional<TimelineDTO> findByUID(String uid) {
        Optional<Timeline> foundTimeline = timelineRepository.findById(uid);

        if (foundTimeline.isPresent()) {
            TimelineDTO foundDTO = objectMapper.convertValue(foundTimeline.get(), TimelineDTO.class);
            return Optional.of(foundDTO);
        }
        return Optional.empty();
    }

    public TimelineDTO update(String uid, TimelineDTO updatedTimelineDTO) {
        Optional<TimelineDTO> foundTimeline = findByUID(uid);

        Timeline updatedTimeline = objectMapper.convertValue(updatedTimelineDTO, Timeline.class);
        updatedTimeline.setUid(UUID.fromString(uid));
        Timeline modifiedTimeline = timelineRepository.save(updatedTimeline);

        return objectMapper.convertValue(modifiedTimeline, TimelineDTO.class);
    }

    public void delete(String uid) {
        timelineRepository.deleteById(uid);
    }
}
