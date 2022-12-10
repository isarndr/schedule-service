package com.codewithisa.scheduleservice.service;

import com.codewithisa.scheduleservice.entity.Schedules;
import com.codewithisa.scheduleservice.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public Schedules saveSchedule(Schedules schedules) {
        log.info("Inside saveSchedule of ScheduleServiceImpl");
        scheduleRepository.save(schedules);
        return schedules;
    }

    @Override
    public Schedules findScheduleByScheduleId(Long scheduleId) {
        log.info("Inside findScheduleByScheduleId of ScheduleServiceImpl");
        return scheduleRepository.findScheduleByScheduleId(scheduleId);
    }

    @Override
    public List<Schedules> findSchedulesByFilmCode(Long filmCode) {
        log.info("Inside findSchedulesByFilmCode of ScheduleServiceImpl");
        return scheduleRepository.findSchedulesByFilmCode(filmCode);
    }

    @Override
    public void deleteScheduleByScheduleId(Long scheduleId) {
        log.info("Inside deleteScheduleByScheduleId of ScheduleServiceImpl");
        scheduleRepository.deleteScheduleByScheduleId(scheduleId);
    }
}

