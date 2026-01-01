package com.popo2381.scheduleapi.controller;

import com.popo2381.scheduleapi.dto.*;
import com.popo2381.scheduleapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetAllScheduleResponse>> getSchedules(@RequestParam(required = false) String writer) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAllByWriter(writer));
    }

    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(@PathVariable Long scheduleId, @RequestBody UpdateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateTitleAndName(scheduleId, request));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId, @RequestBody deleteScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(scheduleService.delete(scheduleId, request));
    }
}
