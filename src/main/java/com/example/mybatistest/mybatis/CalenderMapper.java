package com.example.mybatistest.mybatis;

import com.example.mybatistest.domain.Calender;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalenderMapper {

    @Select("select * from calender_schedule where user_code = #{userID} and user_code = -1")
    public List<Calender> selectAllById(@Param("userID") int userid);

    @Insert("insert into calender_schedule values(#{userCode},#{scheduleYear},#{scheduleMonth},#{scheduleDay},#{scheduleTitle},#{scheduleContent})")
    public void insertCalender(Calender calender);

    @Delete("delete from calender_schedule where schedule_id = #{scheduleID}")
    public void deleteCalender(Calender calender);

    @Update("update calender_schedule set user_code = #{userCode},schedule_year = #{scheduleYear},schedule_month = #{scheduleMonth}," +
            "schedule_day = #{scheduleDay},schedule_title = #{scheduleTitle},schedule_content = #{scheduleContent}" +
            "where schedule_id = #{scheduleID}")
    public void updateCalender(Calender calender);
}
