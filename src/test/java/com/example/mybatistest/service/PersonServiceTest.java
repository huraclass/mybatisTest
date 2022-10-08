package com.example.mybatistest.service;

import com.example.mybatistest.controller.CalenderController;
import com.example.mybatistest.domain.Calender;
import com.example.mybatistest.domain.Person;
import com.example.mybatistest.domain.UserCode;
import com.example.mybatistest.mybatis.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
    @Autowired
    CalenderService service;
    @Autowired
    CalenderController controller;
    @Test
    void selectTest() throws JsonProcessingException {
        String str = " { locations : [\n" +
                "    {\n" +
                "        \"scheduleID\": 4,\n" +
                "        \"userCode\": 9999,\n" +
                "        \"scheduleYear\": 2009,\n" +
                "        \"scheduleMonth\": 9,\n" +
                "        \"scheduleDay\": 9,\n" +
                "        \"title\": \"title\",\n" +
                "        \"scheduleContent\": \"content\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"scheduleID\": 5,\n" +
                "        \"userCode\": 9999,\n" +
                "        \"scheduleYear\": 1000,\n" +
                "        \"scheduleMonth\": 2,\n" +
                "        \"scheduleDay\": 2,\n" +
                "        \"title\": \"content\",\n" +
                "        \"scheduleContent\": \"title\"\n" +
                "    }\n" +
                "] }";
        JSONObject j = new JSONObject(str);
        JSONArray jsonArray = j.getJSONArray("locations");
        List<Calender> list = new ArrayList<>();
//        ObjectMapper objectMapper = new ObjectMapper();
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jj = jsonArray.getJSONObject(i);
//            Calender cal = new Calender(jj.getInt("userCode"),jj.getInt("scheduleYear"),jj.getInt("scheduleMonth"),jj.getInt("scheduleDay"),jj.getString("title"),jj.getString("scheduleContent"),jj.getInt("scheduleID"));
//
//            list.add(cal);
//        }
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println("list.get(i) = " + list.get(i));
//
//        }
    }
}