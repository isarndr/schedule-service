package com.codewithisa.scheduleservice.service;

import com.codewithisa.scheduleservice.VO.Films;
import com.codewithisa.scheduleservice.entity.Schedules;
import com.codewithisa.scheduleservice.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        scheduleRepository.save(schedules);
        return schedules;
    }

    @Override
    public Schedules findScheduleByScheduleId(Long scheduleId) throws Exception{
        Boolean existByScheduleId = existsByScheduleId(scheduleId);

        if(!existByScheduleId){
            log.error("schedule id is not in the database");
            throw new Exception("schedule id is not in the database");
        }

        return scheduleRepository.findScheduleByScheduleId(scheduleId);
    }

    @Override
    public List<Schedules> findSchedulesByFilmCode(Long filmCode) throws Exception{

        Boolean existByFilmCode = existsByFilmCode(filmCode);

        if(!existByFilmCode){
            log.error("film code is not in the database");
            throw new Exception("film code is not in the database");
        }

        return scheduleRepository.findSchedulesByFilmCode(filmCode);
    }

    @Override
    public void deleteScheduleByScheduleId(Long scheduleId) throws Exception{

        Boolean existByScheduleId = existsByScheduleId(scheduleId);

        if(!existByScheduleId){
            log.error("schedule id is not in the database");
            throw new Exception("schedule id is not in the database");
        }

        scheduleRepository.deleteScheduleByScheduleId(scheduleId);
    }

    @Override
    public Schedules findScheduleByJamMulaiAndStudioNameAndTanggalTayangAndFilmCode(
            String jamMulai, Character studioName, String tanggalTayang, Long filmCode
    ) throws Exception{

        Boolean existByFilmCode = existsByFilmCode(filmCode);
        if(!existByFilmCode){
            log.error("film code is not in the database");
            throw new Exception("film code is not in the database");
        }

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
    public List<Schedules> findSchedulesByFilmName(String filmName) throws Exception{
        Films film = restTemplate.getForObject(
                "http://localhost:9002/film/by-film-name/"+filmName,
                Films.class
        );
        if(film==null){
            log.error("film name is not in the database");
            throw new Exception("film name is not in the database");
        }
        Long filmCode = film.getFilmCode();
        List<Schedules> schedulesList= scheduleRepository.findSchedulesByFilmCode(filmCode);
        return schedulesList;
    }

    @Override
    public Boolean existsByFilmCode(Long filmCode) {
        return scheduleRepository.existsByFilmCode(filmCode);
    }

    @Override
    public Boolean existsByScheduleId(Long scheduleId) {
        return scheduleRepository.existsById(scheduleId);
    }
}

