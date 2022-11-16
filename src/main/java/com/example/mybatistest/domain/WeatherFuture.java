package com.example.mybatistest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class WeatherFuture implements Weather{
    private long time;
    private  double maxTemp;
    private double minTemp;
    private String weatherState;
    private double pop;
}
