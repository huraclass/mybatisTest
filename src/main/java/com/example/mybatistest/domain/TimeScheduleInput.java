package com.example.mybatistest.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TimeScheduleInput {
    private String userCode;
    private String subject;
    private String professor;
    private String day;
    private String startTime;
    private String endTime;
    private String room;
    private String alarm;
}
