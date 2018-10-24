package org.depromeet.fill_day.repository;

import org.depromeet.fill_day.domain.entity.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimelineRepository extends JpaRepository<Timeline, String> {
}
