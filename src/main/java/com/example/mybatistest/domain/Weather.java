package com.example.mybatistest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class Weather {
    private String name;
    private String description;
    private double temp;
    private int humidity;
    private double feelsLike;
}
