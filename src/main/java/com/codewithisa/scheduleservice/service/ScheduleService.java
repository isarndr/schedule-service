package com.codewithisa.scheduleservice.service;

import com.codewithisa.scheduleservice.entity.Schedules;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ScheduleService {
    Schedules saveSchedule(Schedules schedules);
    Schedules findScheduleByScheduleId(Long scheduleId);

    List<Schedules> findSchedulesByFilmCode(Long filmCode);
    void deleteScheduleByScheduleId(Long scheduleId);
}

