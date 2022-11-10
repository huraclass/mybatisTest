package com.example.mybatistest.mybatis;

import com.example.mybatistest.domain.Weather;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WeatherMapper {
//    private String name;
//    private String description;
//    private double temp;
//    private int humidity;
//    private double feelsLike;

    @Insert("insert into weather(name,description,temp,humidity,feelsLike) values(#{},#{},#{},#{},#{})")
    void insertWeather(Weather weather);

    @Select("select * from weather limit 1")
    Weather selectWeather();
}
