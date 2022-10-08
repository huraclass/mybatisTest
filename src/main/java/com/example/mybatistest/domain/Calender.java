package com.example.mybatistest.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@Setter
@ToString
public class Calender {
    private int scheduleID;
    private int userCode;
    private int scheduleYear;
    private int scheduleMonth;
    private int scheduleDay;
    private String title;
    private String scheduleContent;

    public static Calender calenderMapper(CalenderInput input) {
        return Calender.builder()
                .scheduleID(Integer.parseInt(input.getScheduleID()))
                .userCode(Integer.parseInt(input.getUserCode()))
                .scheduleYear(Integer.parseInt(input.getScheduleYear()))
                .scheduleMonth(Integer.parseInt(input.getScheduleMonth()))
                .scheduleDay(Integer.parseInt(input.getScheduleDay()))
                .title(input.getTitle())
                .scheduleContent(input.getScheduleContent())
                .build();
    }

}
