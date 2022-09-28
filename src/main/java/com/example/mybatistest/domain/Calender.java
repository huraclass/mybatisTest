package com.example.mybatistest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
@Setter
public class Calender {
    private int scheduleID;
    private int userCode;
    private int scheduleYear;
    private int scheduleMonth;
    private int scheduleDay;
    private String title;
    private String scheduleContent;
}
