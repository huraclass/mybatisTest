package com.example.mybatistest.service;

import com.example.mybatistest.controller.CalenderController;
import org.springframework.beans.factory.annotation.Autowired;

class PersonServiceTest {
    @Autowired
    CalenderService service;
    @Autowired
    CalenderController controller;
//    @Test
//    void selectTest() throws JsonProcessingException {
//        String str = " { locations : [\n" +
//                "    {\n" +
//                "        \"scheduleID\": 4,\n" +
//                "        \"userCode\": 9999,\n" +
//                "        \"scheduleYear\": 2009,\n" +
//                "        \"scheduleMonth\": 9,\n" +
//                "        \"scheduleDay\": 9,\n" +
//                "        \"title\": \"title\",\n" +
//                "        \"scheduleContent\": \"content\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"scheduleID\": 5,\n" +
//                "        \"userCode\": 9999,\n" +
//                "        \"scheduleYear\": 1000,\n" +
//                "        \"scheduleMonth\": 2,\n" +
//                "        \"scheduleDay\": 2,\n" +
//                "        \"title\": \"content\",\n" +
//                "        \"scheduleContent\": \"title\"\n" +
//                "    }\n" +
//                "] }";
//        JSONObject j = new JSONObject(str);
//        JSONArray jsonArray = j.getJSONArray("locations");
//        List<Calender> list = new ArrayList<>();
//        ObjectMapper objectMapper = new ObjectMapper();
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jj = jsonArray.getJSONObject(i);
//            Calender cal = new Calender(jj.getInt("userCode"),jj.getInt("scheduleYear"),jj.getInt("scheduleMonth"),jj.getInt("scheduleDay"),jj.getString("title"),jj.getString("scheduleContent"),jj.getInt("scheduleID"));
//            list.add(cal);
//        }
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println("list.get(i) = " + list.get(i));
//
//        }
//    }
}