package com.mamison.difman.repository;

import com.mamison.difman.entity.Daily;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface Dailyrepository extends JpaRepository<Daily,Long> {
    List<Daily> findAllByDailyDateAfter(Date date);
}
