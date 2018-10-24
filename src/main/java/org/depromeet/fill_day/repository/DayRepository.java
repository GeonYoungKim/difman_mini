package org.depromeet.fill_day.repository;

import org.depromeet.fill_day.domain.entity.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DayRepository extends JpaRepository<Day, String> {
}
