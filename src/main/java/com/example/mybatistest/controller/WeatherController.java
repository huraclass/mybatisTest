package com.example.mybatistest.controller;

import com.example.mybatistest.domain.Weather;
import com.example.mybatistest.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @RequestMapping("/select")
    public List<Weather> weatherSelectController() {
        List<Weather> list = new ArrayList<>();
        list.add(weatherService.getWeather());
        return list;
    }
}
