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
}
