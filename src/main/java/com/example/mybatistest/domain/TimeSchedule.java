package com.example.mybatistest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TimeSchedule {
    private int scheduleID;
    private int userCode;
    private String subject;
    private String professor;
    private int day;
    private int startTime;
    private int endTime;
    private String room;
    private int alarm;
}
