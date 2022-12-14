package com.example.mybatistest.Notice;

import java.io.*;
import java.lang.Integer;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = "/Notice/Modify")
public class NoticeModify extends HttpServlet 
{
	private static final long serialVersionUID = 2L;
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://database-1.cuqcz3ydffen.ap-northeast-2.rds.amazonaws.com:3306/test";
	private final String USER = "admin";
	private final String PASS = "testadmindb";
 	
 	public NoticeModify() 
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

		int id = Integer.parseInt(request.getParameter("Id"));
		String name = request.getParameter("Name");
		String content = request.getParameter("Content");
		JSONObject resJson = new JSONObject();
		JSONArray j = new JSONArray();
		if( ModifyData(id,name,content) ) {
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
	
 	public boolean ModifyData(int id,String name,String content)
	{
	   Connection conn = null;
	   Statement stmt = null;
       Boolean rtn = null;

       try {
          conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
          if( id > 0)
          {
        	  String strUpdate = "";
        	  
        	  if( !name.isEmpty() )
				  strUpdate = name;
        	  if( !content.isEmpty() )
        		  if(!strUpdate.isEmpty())
        			  strUpdate = content;
        		  else
        			  strUpdate = content;
        	  
	          String strQuery = String.format("update notice set %s where ID = %d", strUpdate, id);
	
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

