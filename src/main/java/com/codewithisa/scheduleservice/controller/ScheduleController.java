package com.codewithisa.scheduleservice.controller;

import com.codewithisa.scheduleservice.entity.Schedule;
import com.codewithisa.scheduleservice.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping("/")
    public ResponseEntity<Schedule> saveSchedule(@RequestBody Schedule schedule) {
        return new ResponseEntity<>(scheduleService.saveSchedule(schedule), HttpStatus.CREATED);
    }

    @Operation(summary = "untuk mendapatkan schedule berdasarkan schedule id")
    @GetMapping("/{scheduleId}")
    public ResponseEntity<?> findScheduleByScheduleId(
            @Schema(example = "Nemo") @PathVariable("scheduleId") Long scheduleId) {
        try {
            return new ResponseEntity<>(scheduleService.findScheduleByScheduleId(scheduleId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/by-film-code/{filmCode}")
    public ResponseEntity<?> findSchedulesByFilmCode(@Schema(example = "1")
                                                                   @PathVariable("filmCode") Long filmCode){
        try {
            return new ResponseEntity<>(scheduleService.findSchedulesByFilmCode(filmCode), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<String> deleteScheduleByScheduleId(@PathVariable("scheduleId") Long scheduleId){
        try {
            scheduleService.deleteScheduleByScheduleId(scheduleId);
            return new ResponseEntity<>("Schedule deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/by-all/")
    public ResponseEntity<?> findScheduleByJamMulaiAndStudioNameAndTanggalTayangAndFilmCode(
            @RequestParam("jamMulai") String jamMulai,
            @RequestParam("studioName") Character studioName,
            @RequestParam("tanggalTayang") String tanggalTayang,
            @RequestParam("filmCode") Long filmCode){

        Schedule schedule = null;

        try {
            schedule = scheduleService.findScheduleByJamMulaiAndStudioNameAndTanggalTayangAndFilmCode(
                    jamMulai,studioName,tanggalTayang,filmCode
            );
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @GetMapping("/by-film-name/{filmName}")
    public ResponseEntity<List<Schedule>> findSchedulesByFilmName(@Schema(example = "Nemo")
                                                                   @PathVariable("filmName") String filmName){

        try {
            List<Schedule> scheduleList = scheduleService.findSchedulesByFilmName(filmName);
            return new ResponseEntity<>(scheduleList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("film name is not in the database");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}