package com.example.mybatistest.service;

import com.example.mybatistest.domain.Weather;
import com.example.mybatistest.mybatis.WeatherMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@EnableScheduling
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherMapper weatherMapper;

    @Scheduled(cron = "006***")
    private void autoInsertWeather() throws Exception {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Seongnam-si&appid=e7569e766bcbd4081766788d906cfe65");
        URLConnection conn = url.openConnection();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonParsing(conn));
        JSONObject jsonObject = (JSONObject) obj;
        Weather weather = createWeatherObjectByJSONObject(jsonObject);
        weatherMapper.insertWeather(weather);
    }

    public Weather getWeather() {
        return weatherMapper.selectWeather();
    }

    private Weather createWeatherObjectByJSONObject(JSONObject jsonObject) {
        JSONArray arr = (JSONArray) jsonObject.get("weather");
        JSONObject j = (JSONObject) arr.get(0);
        JSONObject main = (JSONObject) jsonObject.get("main");

//        String name = (String) jsonObject.get("name");
//        String description = (String) j.get("description");
//        double temp = (Math.round(((Double) main.get("temp")-273.15)*100)/100.0);
//        String humidityParse = main.get("humidity").toString();
//        int humidity = Integer.parseInt(humidityParse);
//        double feelsLike = (Math.round(((Double) main.get("feels_like")-273.15)*100)/100.0);

        return Weather.builder()
                .name((String) jsonObject.get("name"))
                .description((String)j.get("description"))
                .temp((Math.round(((Double) main.get("temp")-273.15)*100)/100.0))
                .humidity(Integer.parseInt(main.get("humidity").toString()))
                .feelsLike((Math.round(((Double) main.get("feels_like")-273.15)*100)/100.0))
                .build();
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
