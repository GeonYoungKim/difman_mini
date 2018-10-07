package com.mamison.difman.repository;

import com.mamison.difman.entity.DailyTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyTimeRepository  extends JpaRepository<DailyTime,Long> {
}
