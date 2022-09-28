package com.example.mybatistest.service;

import com.example.mybatistest.mybatis.TimeScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeScheduleService {
    private final TimeScheduleMapper mapper;

}
