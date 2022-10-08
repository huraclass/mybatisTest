package com.example.mybatistest.mybatis;

import com.example.mybatistest.domain.Calender;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalenderMapper {

    @Select("select * from calender_schedule where user_code = #{userCode} or user_code = -1")
    public List<Calender> selectAllById(@Param("userCode") int userCode);

    @Insert("insert into calender_schedule values(#{scheduleID},#{userCode},#{scheduleYear},#{scheduleMonth},#{scheduleDay},#{title},#{scheduleContent})")
    public void insertCalender(Calender calender);

    @Delete("delete from calender_schedule where schedule_id = #{scheduleID} and user_code = #{userCode}")
    public void deleteCalender(@Param("scheduleID")int scheduleID,@Param("userCode")int userCode);

    @Update("update calender_schedule set schedule_year = #{scheduleYear},schedule_month = #{scheduleMonth}," +
            "schedule_day = #{scheduleDay},schedule_title = #{title},schedule_content = #{scheduleContent}" +
            "where schedule_id = #{scheduleID} and user_code = #{userCode}")
    public void updateCalender(Calender calender);
}
