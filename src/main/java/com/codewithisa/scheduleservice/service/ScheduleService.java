package com.codewithisa.scheduleservice.service;

import com.codewithisa.scheduleservice.entity.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ScheduleService {
    Schedule saveSchedule(Schedule schedule);
    Schedule findScheduleByScheduleId(Long scheduleId) throws Exception;

    List<Schedule> findSchedulesByFilmCode(Long filmCode) throws Exception;
    void deleteScheduleByScheduleId(Long scheduleId) throws Exception;

    Schedule findScheduleByJamMulaiAndStudioNameAndTanggalTayangAndFilmCode(
            String jamMulai,
            Character studioName,
            String tanggalTayang,
            Long filmCode
    ) throws Exception;
    List<Schedule> findSchedulesByFilmName(String filmName) throws Exception;
    Boolean existsByFilmCode(Long filmCode);
    Boolean existsByScheduleId(Long scheduleId);
}

