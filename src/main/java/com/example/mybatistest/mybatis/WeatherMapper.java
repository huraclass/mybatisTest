package com.example.mybatistest.mybatis;


import com.example.mybatistest.domain.TimeSchedule;
import com.example.mybatistest.domain.WeatherCurrent;

import com.example.mybatistest.domain.WeatherFuture;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface WeatherMapper {
//    private String name;
//    private String description;
//    private double temp;
//    private int humidity;
//    private double feelsLike;

//    @Insert("insert into time_schedule values(#{scheduleID},#{userCode},#{subject},#{professor},#{day},#{startTime},#{endTime},#{room},#{alarm})")
//    public void insertIntoTimeSchedule(TimeSchedule timeSchedule);

    @Insert("insert into weather_current values(#{time},#{maxTemp},#{minTemp},#{temp},#{feelsLike},#{humidity},#{weatherState})")
    void insertCurrentWeather(WeatherCurrent currentWeather);

    @Insert("insert into weather_future values(#{time},#{maxTemp},#{minTemp},#{weatherState},#{pop})")
    void insertFutureWeather(WeatherFuture weatherFuture);

    @Select("select * from weather_current order by time desc limit 1")
    WeatherCurrent selectWeatherCurrent();

    @Select("select * from weather_future order by time asc")
    List<WeatherFuture> selectAllWeatherFuture();

    @Delete("delete from weather_future")
    void deleteAllWeatherFuture();

    @Delete("delete from weather_current")
    void deleteAllWeatherCurrent();
}
