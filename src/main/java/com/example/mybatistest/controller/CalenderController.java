package com.example.mybatistest.controller;

import com.example.mybatistest.domain.Calender;
import com.example.mybatistest.domain.UserCode;
import com.example.mybatistest.service.CalenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<Calender> selectAllCalender(@RequestBody UserCode userCode) {
        List<Calender> calenderList = service.findAllCalender(userCode.getUserCode());
        return calenderList;
    }

    //insert
    @RequestMapping("/insert")
    public ResponseEntity insertCalender(@RequestBody Calender calender) {
        try {
            service.insertCalender(calender);
        } catch (Exception e) {
            log.error(calender + "객체 insert 중 에러 발생");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    //delete
    @RequestMapping("/delete")
    public ResponseEntity deleteCalender(@RequestBody UserCode scheduleID,@RequestBody UserCode userCode) {
        try {
            service.deleteCalender(scheduleID.getUserCode(),userCode.getUserCode());
        } catch (Exception e) {
            log.error(scheduleID + "객체 delete 중 에러 발생");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    //update
    @RequestMapping("/update")
    public ResponseEntity updateCalender(@RequestBody Calender calender) {
        try {
            service.updateCalender(calender);
        } catch (Exception e) {
            log.error(calender + "객체 update 중 에러 발생");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/test")
    public List<Calender> test(@RequestBody Calender calender) {
        System.out.println("calender = " + calender);
        List<Calender> list = new ArrayList<>();
        list.add(calender);
        list.add(new Calender(20, 200, 1010, 10, 10, "tiger", "is"));
        return list;
    }

    @RequestMapping("/test2")
    public String test2() {
        return "hello";
    }
}
