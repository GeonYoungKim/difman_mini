package org.depromeet.fill_day.repository;

import org.depromeet.fill_day.domain.entity.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TimelineRepository extends JpaRepository<Timeline, UUID> {
}
