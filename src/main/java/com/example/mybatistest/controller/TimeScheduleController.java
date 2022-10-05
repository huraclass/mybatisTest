package com.example.mybatistest.controller;

import com.example.mybatistest.domain.TimeSchedule;
import com.example.mybatistest.service.TimeScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/timeschedule")
@RequiredArgsConstructor
public class TimeScheduleController {

    private final TimeScheduleService service;

    //search
    @RequestMapping("/search")
    public List<TimeSchedule> searchTimeScheduleByID(@RequestParam("userCode") int userCode) {
        return service.findTimeSchedule(userCode);
    }

    //insert
    @RequestMapping("/insert")
    public ResponseEntity insertTimeSchedule(@RequestBody TimeSchedule timeSchedule) {
        try {
            service.insertTimeSchedule(timeSchedule);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    //delete
    @RequestMapping("/delete")
    public ResponseEntity deleteTimeSchedule(@RequestParam("userCode") int userCode) {
        try {
            service.deleteTimeScheduleService(userCode);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
