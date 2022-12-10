package com.codewithisa.scheduleservice.controller;

import com.codewithisa.scheduleservice.entity.Schedules;
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
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @PostMapping("/")
    public ResponseEntity<Schedules> saveSchedule(@RequestBody Schedules schedule) {
        log.info("Inside saveSchedule of ScheduleController");
        return new ResponseEntity<>(scheduleService.saveSchedule(schedule), HttpStatus.CREATED);
    }

    @Operation(summary = "untuk mendapatkan schedule berdasarkan schedule id")
    @GetMapping("/{scheduleId}")
    public ResponseEntity<Schedules> findScheduleByScheduleId(
            @Schema(example = "Nemo") @PathVariable("scheduleId") Long scheduleId) {
        log.info("Inside findScheduleByScheduleId of ScheduleController");
        return new ResponseEntity<>(scheduleService.findScheduleByScheduleId(scheduleId), HttpStatus.OK);
    }

    @GetMapping("/schedules-by-film-code/{filmCode}")
    public ResponseEntity<List<Schedules>> findSchedulesByFilmCode(@Schema(example = "1")
                                                                   @PathVariable("filmCode") Long filmCode){
        log.info("Inside findScheduleByScheduleId of ScheduleController");
        return new ResponseEntity<>(scheduleService.findSchedulesByFilmCode(filmCode), HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<String> deleteScheduleByScheduleId(@PathVariable("scheduleId") Long scheduleId){
        log.info("Inside deleteScheduleByScheduleId of ScheduleController");
        scheduleService.deleteScheduleByScheduleId(scheduleId);
        return new ResponseEntity<>("Schedule deleted", HttpStatus.OK);
    }
}