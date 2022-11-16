package com.example.mybatistest.Notice;

import java.io.*;
import java.lang.Integer;
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

@WebServlet(urlPatterns = "/Notice/Delete")
public class NoticeDelete extends HttpServlet 
{
	private static final long serialVersionUID = 2L;
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://database-1.cuqcz3ydffen.ap-northeast-2.rds.amazonaws.com:3306/test";
	private final String USER = "admin";
	private final String PASS = "testadmindb";
 	
 	public NoticeDelete() 
 	{
 		try {
 			Class.forName(JDBC_DRIVER);
 		} catch(Exception e) {
 			System.out.println("Error : JDBC ����̹� �ε� ����");
 		}
	 }

 	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException 
	{
		int id = Integer.parseInt(request.getParameter("Id"));
		int subjectID = Integer.parseInt(request.getParameter("SubjectId"));
		JSONArray j = new JSONArray();
		JSONObject resJson = new JSONObject();
		if( DeleteData(id,subjectID) ) {
			resJson.put("result", "OK");
		}
		else {
			resJson.put("result", "Fail");
		}
		j.add(resJson);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(j.toString());
    }
	
 	public boolean DeleteData(int id,int subjectID)
	{
	   Connection conn = null;
	   Statement stmt = null;
       Boolean rtn = null;

       try {
          conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
          
          if( subjectID > 0)
          {
	          String strQuery = String.format("delete from notice where SubjectID = %d", subjectID);
	
	  		  stmt = conn.createStatement();
	          rtn = stmt.execute(strQuery);
	          return true;
          }
          else
          {
	          if( id > 0 )
	          {
		          String strQuery = String.format("delete from notice where ID = %d", id);
		
		  		  stmt = conn.createStatement();
		          rtn = stmt.execute(strQuery);
		          return true;
	          }
	          else
		          return false;
          }
        	  
       } catch (Exception ex) {
          System.out.println("Exception" + ex);
       } finally {
		  if(stmt!=null) try{stmt.close();}catch(SQLException e){}
	      if(conn!=null) try{conn.close();}catch(SQLException e){}
       }
       
       return true;
	}
}

