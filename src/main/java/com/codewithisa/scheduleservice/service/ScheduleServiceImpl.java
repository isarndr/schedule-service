package com.codewithisa.scheduleservice.service;

import com.codewithisa.scheduleservice.VO.Film;
import com.codewithisa.scheduleservice.entity.Schedule;
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
    public Schedule saveSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
        log.info("Schedule saved");
        return schedule;
    }

    @Override
    public Schedule findScheduleByScheduleId(Long scheduleId) throws Exception{
        Boolean existByScheduleId = existsByScheduleId(scheduleId);

        if(!existByScheduleId){
            log.error("schedule id is not in the database");
            throw new Exception("schedule id is not in the database");
        }

        return scheduleRepository.findScheduleByScheduleId(scheduleId);
    }

    @Override
    public List<Schedule> findSchedulesByFilmCode(Long filmCode) throws Exception{

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
    public Schedule findScheduleByJamMulaiAndStudioNameAndTanggalTayangAndFilmCode(
            String jamMulai, Character studioName, String tanggalTayang, Long filmCode
    ) throws Exception{

        Boolean existByFilmCode = existsByFilmCode(filmCode);
        if(!existByFilmCode){
            log.error("film code is not in the database");
            throw new Exception("film code is not in the database");
        }

        Schedule schedule = scheduleRepository.findScheduleByJamMulaiAndStudioNameAndTanggalTayangAndFilmCode(
                jamMulai, studioName,tanggalTayang,filmCode
        );
        if(schedule.equals(null)){
            log.error("schedule is not found");
            throw new Exception("Schedule tidak ditemukan");
        }
        return schedule;
    }

    @Override
    public List<Schedule> findSchedulesByFilmName(String filmName) throws Exception{
        Film film = restTemplate.getForObject(
                "http://localhost:9002/film/by-film-name/"+filmName,
                Film.class
        );
        if(film==null){
            log.error("film name is not in the database");
            throw new Exception("film name is not in the database");
        }
        Long filmCode = film.getFilmCode();
        List<Schedule> scheduleList = scheduleRepository.findSchedulesByFilmCode(filmCode);
        return scheduleList;
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

