package com.codewithisa.scheduleservice.repository;

import com.codewithisa.scheduleservice.entity.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedules, Long> {
    @Query(
            nativeQuery = true,
            value = "select * from schedules where schedule_id=:scheduleId"
    )
    Schedules findScheduleByScheduleId(@Param("scheduleId") Long scheduleId);

    @Query(
            nativeQuery = true,
            value = "select * from schedules where film_code=:film_code"
    )
    List<Schedules> findSchedulesByFilmCode(@Param("film_code") Long film_code);
}

