package com.example.mybatistest.Notice;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

@WebServlet(urlPatterns = "/Notice/Insert")
public class NoticeInsert extends HttpServlet 
{
	private static final long serialVersionUID = 2L;
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://database-1.cuqcz3ydffen.ap-northeast-2.rds.amazonaws.com:3306/test";
	private final String USER = "admin";
	private final String PASS = "testadmindb";
 	
 	public NoticeInsert() 
 	{
 		try {
 			Class.forName(JDBC_DRIVER);
 		} catch(Exception e) {
 			System.out.println("Error : JDBC 드라이버 로딩 실패");
 		}
	 }

 	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException 
	{
		StringBuffer jsonBuffer = new StringBuffer();
		String strLine = null;

		BufferedReader reader = request.getReader();
		while ((strLine = reader.readLine()) != null)
			jsonBuffer.append(strLine);

		//ServletContext context = getServletContext( );
		//context.log(jsonBuffer.toString());
		
		JSONObject reqJson = new JSONObject();
		JSONParser parser = new JSONParser();
		
		try {
			reqJson = (JSONObject)parser.parse(jsonBuffer.toString());
		} catch(ParseException e) {
			System.out.println("변환에 실패");
			e.printStackTrace();
		}
			
		//context.log(reqJson.get("Name").toString());

		JSONObject resJson = new JSONObject();
		if( InsertData(reqJson) )
			resJson.put("result", "OK");
		else
			resJson.put("result", "Fail");
				
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(resJson.toString());
    }
	
	public boolean InsertData(JSONObject jsonData)
	{
	   Connection conn = null;
	   Statement stmt = null;
       Boolean rtn = null;

       try {
          conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
          int id = Integer.parseInt(jsonData.get("SubjectID").toString());
          if( id > 0)
          {
	          String strQuery = String.format("insert into notice (SubjectID, Name, Content) values (%d, '%s', '%s')", id, jsonData.get("Name").toString(), jsonData.get("Content").toString());
	          //ServletContext context = getServletContext( );
	  		  //context.log(strQuery);
	
	  		  stmt = conn.createStatement();
	          rtn = stmt.execute(strQuery);
	          return true;
          }
          else
        	  return false;
       } catch (Exception ex) {
          System.out.println("Exception" + ex);
       } finally {
		  if(stmt!=null) try{stmt.close();}catch(SQLException e){}
	      if(conn!=null) try{conn.close();}catch(SQLException e){}
       }
       
       return true;
	}
}

