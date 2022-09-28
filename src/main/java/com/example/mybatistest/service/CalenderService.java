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
        List<Calender> list = mapper.selectAllById(userCode);
        return list;
    }

    //insert
    public void insertCalender(Calender calender) {
        mapper.insertCalender(calender);
    }

    //delete
    public void deleteCalender(Calender calender) {
        mapper.deleteCalender(calender);
    }

    //update
    public void updateCalender(Calender calender) {
        mapper.updateCalender(calender);
    }
}
