package com.mamison.difman.repository;

import com.mamison.difman.entity.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyTimeRepository  extends JpaRepository<TimeLine,Long> {
}
