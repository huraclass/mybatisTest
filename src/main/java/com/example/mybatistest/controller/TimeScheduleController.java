package com.example.mybatistest.controller;

import com.example.mybatistest.domain.TimeSchedule;
import com.example.mybatistest.domain.TimeScheduleInput;
import com.example.mybatistest.domain.UserCode;
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
    public List<TimeSchedule> searchTimeScheduleByID(UserCode code) {
        System.out.println("userCode = " + code);
        return service.findTimeSchedule(Integer.parseInt(code.getUserCode()));
    }

    //insert
    @RequestMapping("/insert")
    public void insertTimeSchedule(TimeScheduleInput timeSchedule) {
        TimeSchedule time = TimeSchedule.timeScheduleMapper(timeSchedule);
        try {
            service.insertTimeSchedule(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //delete
    @RequestMapping("/delete")
    public void deleteTimeSchedule(UserCode userCode) {
        try {
            service.deleteTimeScheduleService(Integer.parseInt(userCode.getUserCode()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
