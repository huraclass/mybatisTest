package com.example.mybatistest.weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WeatherGet {
//    public void getWeather() throws IOException {
//        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"); /*URL*/
//        urlBuilder.append("?" + URLEncoder.encode("ypOUrIzpYHLWqJH3LtYo%2BhRMMu6lifXqjtJ%2Bu4JqyVcIG56X%2BjNCl%2Fe3s59vYThiNopJ%2BIwakBBOFiFUjcQzgA%3D%3D","UTF-8") + "=서비스키"); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
//        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
//        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20210628", "UTF-8")); /*‘21년 6월 28일 발표*/ //몰?루
//        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0600", "UTF-8")); /*06시 발표(정시단위) */
//        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("55", "UTF-8")); /*예보지점의 X 좌표값*/   //몰?루
//        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점의 Y 좌표값*/  //몰?루
//        URL url = new URL(urlBuilder.toString());
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
//        BufferedReader rd;
//        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
//        String json = sb.toString();
//    }
//*/
//    private void jsonMapper(String json) {
//        String jsonStr = json;
//        JSONObject jsonObject = new JSONObject(jsonStr);
//        JSONObject responseJsonObject = jsonObject.getJSONObject("response");
//        JSONObject jsonBody = responseJsonObject.getJSONObject("body");
//        JSONObject jsonItems = jsonBody.getJSONObject("items");
//        JSONArray jsonArray = jsonItems.getJSONArray("item");
//
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject arrayJSONObject = jsonArray.getJSONObject(i);
//            String data = arrayJSONObject.getString("baseDate");
//            String time = arrayJSONObject.getString("baseTime");
//            String category = arrayJSONObject.getString("category");
//            int x = arrayJSONObject.getInt("nx");
//            int y = arrayJSONObject.getInt("ny");
//            String value = arrayJSONObject.getString("obsrValue");
//            Weather item = Weather.builder()
//                    .date(data)
//                    .time(time)
//                    .category(category)
//                    .x(x)
//                    .y(y)
//                    .value(value)
//                    .build();
//            System.out.println("item = " + item);
//        }
//    }
}
