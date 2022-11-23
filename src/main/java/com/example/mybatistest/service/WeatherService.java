package com.example.mybatistest.service;

import com.example.mybatistest.domain.Weather;
import com.example.mybatistest.domain.WeatherCurrent;
import com.example.mybatistest.domain.WeatherFuture;
import com.example.mybatistest.mybatis.WeatherMapper;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class WeatherService {
    String urlInfo = "https://api.openweathermap.org/data/3.0/onecall?lat=37.4386&lon=-127.1378&exclude=alert,minutely,hourly&lang=kr&units=metric&appid=e7569e766bcbd4081766788d906cfe65";
    private final WeatherMapper weatherMapper;

    @Scheduled(cron = "0 0 0/1 * * *")
    private void insertCurrentWeather() throws Exception {
        JSONObject json = getWeatherInfoToJsonObject();
        JSONArray array = (JSONArray) json.get("daily");
        JSONObject futureJson = (JSONObject) array.get(1);
        JSONObject futureTempJson = (JSONObject) futureJson.get("temp");
        JSONObject currentJson = (JSONObject) json.get("current");
        JSONArray weatherArray = (JSONArray) currentJson.get("weather");
        JSONObject weatherObject = (JSONObject) weatherArray.get(0);

        WeatherCurrent currentWeather = WeatherCurrent.builder()
                .temp((Double) currentJson.get("temp"))
                .feelsLike((Double) currentJson.get("feels_like"))
                .humidity((long) currentJson.get("humidity"))
                .time((long) currentJson.get("dt"))
                .maxTemp((double) futureTempJson.get("max"))
                .minTemp((double) futureTempJson.get("min"))
                .weatherState((String) weatherObject.get("description"))
                .build();

        weatherMapper.insertCurrentWeather(currentWeather);
    }
//@Scheduled(cron = "*/1 * * * * *")

    @Scheduled(cron = "0 0 13 * * *")
    private void insertFutureWeather() throws Exception {
        weatherMapper.deleteAllWeatherFuture();
        weatherMapper.deleteAllWeatherCurrent();

        List<WeatherFuture> weather = new ArrayList<>();
        JSONObject json = getWeatherInfoToJsonObject();
        JSONArray array = (JSONArray) json.get("daily");

        for (int i = 0; i < array.size() - 1; i++) {
            JSONObject futureJson = (JSONObject) array.get(i);
            JSONObject temp = (JSONObject) futureJson.get("temp");
            JSONArray wea = (JSONArray) futureJson.get("weather");
            JSONObject futureWeather = (JSONObject) wea.get(0);

            double pip = 0;
            double max = (double)temp.get("max");
            double min = (double)temp.get("min");
            if (!futureJson.get("pop").getClass().getName().equals("java.lang.Long")) {
                pip = (double) futureJson.get("pop");
            }
            weather.add(WeatherFuture.builder()
                    .weatherState((String) futureWeather.get("description"))
                    .time((long) futureJson.get("dt"))
                    .maxTemp((double) temp.get("max"))
                    .minTemp((double) temp.get("min"))
                    .pop(pip)
                    .build());
        }
        weather.forEach(weatherFuture -> weatherMapper.insertFutureWeather(weatherFuture));
    }
//    @Scheduled(cron = "*/1 * * * * *")
//    private void insertFutureWeather() throws Exception {
//        List<WeatherFuture> weather = new ArrayList<>();
//        JSONObject json = getWeatherInfoToJsonObject();
//        JSONArray array = (JSONArray) json.get("daily");
//
//        for (int i = 1; i < array.size(); i++) {
//            JSONObject futureJson = (JSONObject) array.get(i);
//            JSONObject temp = (JSONObject) futureJson.get("temp");
//            JSONArray wea = (JSONArray) futureJson.get("weather");
//            JSONObject futureWeather = (JSONObject) wea.get(0);
//
//            double pip = 0;
//            double max = (double)temp.get("max");
//            double min = (double)temp.get("min");
//            if (!futureJson.get("pop").getClass().getName().equals("java.lang.Long")) {
//                pip = (double) futureJson.get("pop");
//            }
//            weather.add(WeatherFuture.builder()
//                    .weatherState((String) futureWeather.get("description"))
//                    .time((long) futureJson.get("dt"))
//                    .maxTemp(max)
//                    .minTemp(min)
//                    .pop(pip)
//                    .build());
//        }
//        weather.forEach(weatherFuture -> weatherMapper.insertFutureWeather(weatherFuture));
//    }


    public List<Weather> getCurrentWeather() {
        List<Weather> list = new ArrayList<>();
        list.add(weatherMapper.selectWeatherCurrent());
        list.addAll(weatherMapper.selectAllWeatherFuture());
        return list;
    }


    private JSONObject getWeatherInfoToJsonObject() throws Exception {
        URL url = new URL(urlInfo);
        URLConnection conn = url.openConnection();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonParsing(conn));
        return (JSONObject) obj;
    }

    private String jsonParsing(URLConnection conn) {
        String line = null;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                return line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
