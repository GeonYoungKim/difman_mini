package org.depromeet.fill_day.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.depromeet.fill_day.domain.dto.DayDTO;
import org.depromeet.fill_day.domain.entity.Day;
import org.depromeet.fill_day.exception.NotFoundException;
import org.depromeet.fill_day.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class DayService {

    private DayRepository dayRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public DayService(DayRepository dayRepository,
                      ObjectMapper objectMapper) {
        this.dayRepository = dayRepository;
        this.objectMapper = objectMapper;
    }

    public DayDTO create(DayDTO newDayDTO) {
        Day newDay = objectMapper.convertValue(newDayDTO, Day.class);
        Day createdDay = dayRepository.save(newDay);

        return objectMapper.convertValue(createdDay, DayDTO.class);
    }

    public Optional<DayDTO> findByUID(String uid) {
        Optional<Day> foundDay = dayRepository.findById(UUID.fromString(uid));

        if (foundDay.isPresent()) {
            DayDTO foundDTO = objectMapper.convertValue(foundDay.get(), DayDTO.class);
            return Optional.of(foundDTO);
        }
        return Optional.empty();
    }

    public Optional<DayDTO> findByDay(Date date) {
        Optional<Day> foundDay = dayRepository.findByToday(date);

        if (foundDay.isPresent()) {
            DayDTO foundDTO = objectMapper.convertValue(foundDay.get(), DayDTO.class);
            return Optional.of(foundDTO);
        }
        return Optional.empty();
    }

    public DayDTO update(String uid, DayDTO updatedDayDTO) {
        Optional<DayDTO> foundDay = findByUID(uid);
        foundDay.orElseThrow(NotFoundException::new);

        Day updatedDay = objectMapper.convertValue(updatedDayDTO, Day.class);
        updatedDay.setUid(UUID.fromString(uid));
        Day modifiedDay = dayRepository.save(updatedDay);

        return objectMapper.convertValue(modifiedDay, DayDTO.class);
    }
}
