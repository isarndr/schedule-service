package com.codewithisa.scheduleservice.service;

import com.codewithisa.scheduleservice.entity.Schedules;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ScheduleService {
    Schedules saveSchedule(Schedules schedules);
    Schedules findScheduleByScheduleId(Long scheduleId) throws Exception;

    List<Schedules> findSchedulesByFilmCode(Long filmCode) throws Exception;
    void deleteScheduleByScheduleId(Long scheduleId) throws Exception;

    Schedules findScheduleByJamMulaiAndStudioNameAndTanggalTayangAndFilmCode(
            String jamMulai,
            Character studioName,
            String tanggalTayang,
            Long filmCode
    ) throws Exception;
    List<Schedules> findSchedulesByFilmName(String filmName) throws Exception;
    Boolean existsByFilmCode(Long filmCode);
    Boolean existsByScheduleId(Long scheduleId);
}

