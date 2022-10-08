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

    public static TimeSchedule timeScheduleMapper(TimeScheduleInput input) {
         return TimeSchedule.builder()
                .scheduleID(0)
                .userCode(Integer.parseInt(input.getUserCode()))
                .subject(input.getSubject())
                .professor(input.getProfessor())
                .day(Integer.parseInt(input.getDay()))
                .startTime(Integer.parseInt(input.getStartTime()))
                .endTime(Integer.parseInt(input.getEndTime()))
                .room(input.getRoom())
                .alarm(Integer.parseInt(input.getAlarm()))
                .build();
    }
}
