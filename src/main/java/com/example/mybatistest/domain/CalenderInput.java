package com.example.mybatistest.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CalenderInput {
    private String scheduleID;
    private String userCode;
    private String scheduleYear;
    private String scheduleMonth;
    private String scheduleDay;
    private String title;
    private String scheduleContent;
}
