package com.example.mybatistest.mybatis;

import com.example.mybatistest.domain.TimeSchedule;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TimeScheduleMapper {
    //select
    @Select("select * from time_schedule where user_code = #{user_code}")
    public List<TimeSchedule> searchTimeScheduleByUserCode(int userCode);

    //insert
    @Insert("insert into time_schedule values(#{},#{},#{},#{},#{},#{},#{},#{})")
    public void insertIntoTimeSchedule(TimeSchedule timeSchedule);

    //delete
    @Delete("delete from time_schedule where user_code = #{userCode}")
    public void deleteTimeScheduleByUserCode(int userCode);

    //update


}
