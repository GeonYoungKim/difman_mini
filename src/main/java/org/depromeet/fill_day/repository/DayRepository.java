package org.depromeet.fill_day.repository;

import org.depromeet.fill_day.domain.entity.Account;
import org.depromeet.fill_day.domain.entity.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DayRepository extends JpaRepository<Day, UUID> {
    public Optional<Day> findByToday(Date today);
    public List<Day> findByTodayBetweenAndAccountUidOrderByToday(Date from, Date to, UUID accountUid);
}
