package com.example.mybatistest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class WeatherCurrent implements Weather{
    private long time;
    private  double maxTemp;
    private double minTemp;
    private double temp;
    private double feelsLike;
    private long humidity;
    private String weatherState;
}
