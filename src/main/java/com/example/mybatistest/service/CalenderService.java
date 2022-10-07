package com.example.mybatistest.service;

import com.example.mybatistest.domain.Calender;
import com.example.mybatistest.mybatis.CalenderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalenderService {
    private final CalenderMapper mapper;

    //select
    public List<Calender> findAllCalender(int userCode) {
        return mapper.selectAllById(userCode);
    }

    //insert
    public void insertCalender(Calender calender) {
        mapper.insertCalender(calender);
    }

    //delete
    public void deleteCalender(int scheduleID,int userCode) {
        mapper.deleteCalender(scheduleID,userCode);
    }

    //update
    public void updateCalender(Calender calender) {
        mapper.updateCalender(calender);
    }
}
