package com.example.mybatistest.controller;

import com.example.mybatistest.domain.*;
import com.example.mybatistest.service.CalenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@ResponseBody
@RequestMapping("/calender")
@RequiredArgsConstructor
public class CalenderController {

    private final CalenderService service;

    //select

    @RequestMapping("/select")
    public List<Calender> selectAllCalender(int userCode) {
        List<Calender> calenderList = service.findAllCalender(userCode);
        return calenderList;
    }

    //insert

    @RequestMapping("/insert")
    public void insertCalender(CalenderInput calender) {
        Calender cal = Calender.calenderMapper(calender);

        try {
            service.insertCalender(cal);
        } catch (Exception e) {
            log.error(calender + "객체 insert 중 에러 발생");
        }
    }

    //delete

    @RequestMapping("/delete")
    public void deleteCalender(int scheduleID, UserCode userCode) {
        System.out.println("scheduleID = " + scheduleID);
        System.out.println("userCode = " + userCode);
        try {
            service.deleteCalender(scheduleID,Integer.parseInt(userCode.getUserCode()));
        } catch (Exception e) {
            log.error(scheduleID + "객체 delete 중 에러 발생");
        }
    }

    //update

    @RequestMapping("/update")
    public void updateCalender(CalenderInput calender) {
        Calender cal = Calender.calenderMapper(calender);
        try {
            service.updateCalender(cal);
        } catch (Exception e) {
            log.error(cal + "error");
            log.error(calender + "객체 update 중 에러 발생");
        }
    }

}
