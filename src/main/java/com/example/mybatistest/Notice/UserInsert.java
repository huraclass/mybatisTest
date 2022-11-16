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

@WebServlet(urlPatterns = "/User/Insert")
public class UserInsert extends HttpServlet 
{
	private static final long serialVersionUID = 2L;
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://database-1.cuqcz3ydffen.ap-northeast-2.rds.amazonaws.com:3306/test";
	private final String USER = "admin";
	private final String PASS = "testadmindb";
 	
 	public UserInsert() 
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

		String id = request.getParameter("Id");
		String pass = request.getParameter("Password");

		JSONObject resJson = new JSONObject();
		JSONArray j = new JSONArray();
		if( InsertData(id,pass) )
			resJson.put("result", "OK");
		else
			resJson.put("result", "Fail");
		j.add(resJson);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(j.toString());
    }
	
	public boolean InsertData(String Id,String Password)
	{
	   Connection conn = null;
	   Statement stmt = null;
       Boolean rtn = null;


       try {
          conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
		   String sql = String.format("insert into member(user_pass,id) values ('%s', '%s')", Password,Id);

  		  stmt = conn.createStatement();
          rtn = stmt.execute(sql);
          return true;
       } catch (Exception ex) {
          System.out.println("Exception" + ex);
       } finally {
		  if(stmt!=null) try{stmt.close();}catch(SQLException e){}
	      if(conn!=null) try{conn.close();}catch(SQLException e){}
       }
       
       return false;
	}
}

