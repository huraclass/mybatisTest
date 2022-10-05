package com.example.mybatistest.service;

import com.example.mybatistest.domain.TimeSchedule;
import com.example.mybatistest.mybatis.TimeScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeScheduleService {
    private final TimeScheduleMapper mapper;

    //search
    public List<TimeSchedule> findTimeSchedule(int userCode) {
        return mapper.searchTimeScheduleByUserCode(userCode);
    }

    //insert
    public void insertTimeSchedule(TimeSchedule timeSchedule) {
        mapper.insertIntoTimeSchedule(timeSchedule);
    }
    //delete
    public void deleteTimeScheduleService(int userCode) {
        mapper.deleteTimeScheduleByUserCode(userCode);
    }
    //update



}
