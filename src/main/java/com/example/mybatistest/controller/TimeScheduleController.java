package com.example.mybatistest.controller;

import com.example.mybatistest.domain.TimeSchedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/timeschedule")
public class TimeScheduleController {
    //search
    @RequestMapping("/search/*")
    public List<TimeSchedule> searchTimeScheduleByID(int userCode) {
        List<TimeSchedule> list = null;
        //내용 삽입 바람
        return list;
    }

    //insert
    @RequestMapping("/insert/*")
    public ResponseEntity insertTimeSchedule(TimeSchedule timeSchedule) {

        return new ResponseEntity(HttpStatus.OK);
    }

    //delete
    @RequestMapping("/delete/*")
    public ResponseEntity deleteTimeSchedule(int userCode) {

        return new ResponseEntity(HttpStatus.OK);
    }

    //update
    @RequestMapping("/update/*")
    public TimeSchedule updateTimeSchedule(TimeSchedule timeSchedule) {


        return timeSchedule;
    }
}
