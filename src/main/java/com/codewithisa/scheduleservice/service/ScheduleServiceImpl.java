package com.codewithisa.scheduleservice.service;

import com.codewithisa.scheduleservice.VO.Films;
import com.codewithisa.scheduleservice.entity.Schedules;
import com.codewithisa.scheduleservice.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    RestTemplate restTemplate;

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

    @Override
    public Schedules findScheduleByJamMulaiAndStudioNameAndTanggalTayangAndFilmCode(
            String jamMulai, Character studioName, String tanggalTayang, Long filmCode
    ) throws Exception{
        log.info("Inside findScheduleByJamMulaiAndStudioNameAndTanggalTayangAndFilmCode of ScheduleServiceImpl");
        Schedules schedules = scheduleRepository.findScheduleByJamMulaiAndStudioNameAndTanggalTayangAndFilmCode(
                jamMulai, studioName,tanggalTayang,filmCode
        );
        if(schedules.equals(null)){
            log.error("schedule is not found");
            throw new Exception("Schedules tidak ditemukan");
        }
        return schedules;
    }

    @Override
    public List<Schedules> findSchedulesByFilmName(String filmName) {
        log.info("Inside findSchedulesByFilmName of ScheduleServiceImpl");
        Films film = restTemplate.getForObject(
                "https://film-service-production.up.railway.app/films/find-film-by-film-name/"+filmName,
                Films.class
        );
        Long filmCode = film.getFilmCode();
        List<Schedules> schedulesList= scheduleRepository.findSchedulesByFilmCode(filmCode);
        return schedulesList;
    }
}

