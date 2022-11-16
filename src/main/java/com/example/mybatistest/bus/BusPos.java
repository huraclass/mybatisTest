package com.example.mybatistest.bus;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@WebServlet("/getBusPosition")
public class BusPos extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String[] constraint= {"stId","arrmsgSec1","arrmsgSec2","repTm1","repTm2","stNm","rtNm","gpsX","gpsY","stationTp","firstTm","firstTm","lastTm","isLast1","isLast2","term","routeType",
            "staOrd","vehId1","vehId2","stationNm1","stationNm2","traTime1","traTime2","traSpd1","traSpd2","isArrive1","isArrive2","busType1","busType2","congestion","busRouteId","sectOrd1"
            ,"sectOrd2","adirection","isFullFlag1","isFullFlag2","nxtStn","posX","posY","rerdieDiv1","rerdieDiv2","rerideNum1","rerideNum2","sectNm","deTourAt","tasdasd","plainNo1",
            "plainNo2","arsId","nextBus"};
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusPos() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("Application/json;charset=UTF-8");
        String URL = "jdbc:mysql://database-1.cuqcz3ydffen.ap-northeast-2.rds.amazonaws.com/test";
        String USERNAME = "admin";
        String PASSWORD = "testadmindb";
        String ServiceKey="S3514uGGLwuxIrKD7iUG7ViRFphmsr6MwSOLJkGTCpaqlLQczMfWvmlcYsRxNYTW29JoXf2LvrrLItkP5CDCEA==";
        JSONArray result=new JSONArray();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //복정 파출소 복정 캠퍼스
            JSONObject jsonCampusDir=new JSONObject(getResult(getURL(ServiceKey,"48066")));
            result.put(refineBusList(getBusList(jsonCampusDir)));

            JSONObject jsonSchoolDir=new JSONObject(getResult(getURL(ServiceKey,"48065")));
            result.put(refineBusList(getBusList(jsonSchoolDir)));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.getWriter().append(result.toString());
    }
    public String getURL(String ServiceKey, String arsId) throws Exception {
        StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/stationinfo/getStationByUid"); /*URL*/
        urlBuilder.append("?").append(URLEncoder.encode("serviceKey","UTF-8")).append("=").append(URLEncoder.encode(ServiceKey,"UTF-8")); /*Service Key*/
        urlBuilder.append("&").append(URLEncoder.encode("arsId","UTF-8")).append("=").append(URLEncoder.encode(arsId, "UTF-8")); /*정류소ID*/
        urlBuilder.append("&").append(URLEncoder.encode("resultType","UTF-8")).append("=").append(URLEncoder.encode("json", "UTF-8")); /*정류소ID*/
        return urlBuilder.toString();
    }
    public JSONArray refineBusList(JSONArray json) {
        JSONObject selectobj;
        for(int i=0;i<json.length();i++) {
            selectobj=json.getJSONObject(i);
            for(int i2=0;i2<constraint.length;i2++) {
                selectobj.remove(constraint[i2]);
            }
        }
        return json;
    }
    public JSONArray getBusList(JSONObject json) {
        //정상적으로 받아오지 못하면 빈 JSONArray를 반환
        if(json.getJSONObject("msgHeader").getInt("headerCd")!=0) {
            return new JSONArray();
        }
        return json.getJSONObject("msgBody").getJSONArray("itemList");
    }

    private String getResult(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return sb.toString();
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}