package com.example.mybatistest.mybatis;

import com.example.mybatistest.domain.Person;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

    @Select("select * from person")
    List<Person> findAll();

    @Insert("insert into person(id,age,name) values(#{id},#{age},#{name})")
    void insertPerson(@Param("member")Person person);

    @Select("select * from person where id = #{id}")
    Person findById(Person person);
}
