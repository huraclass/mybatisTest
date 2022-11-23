package com.example.mybatistest.Notice;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@WebServlet(urlPatterns = "/User/Insert")
public class UserInsert extends HttpServlet 
{
	private static final long serialVersionUID = 2L;
 	public UserInsert() 
 	{
 		try {
 			Class.forName(DBConnectionInfo.JDBC_DRIVER);
 		} catch(Exception e) {
 			System.out.println("Error : JDBC 드라이버 로딩 실패");
 		}
	 }

 	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException 
	{

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		JSONArray j = new JSONArray();

		String id = request.getParameter("Id");
		String pass = request.getParameter("Password");

		j.add(InsertData(id,pass));
        out.println(j.toString());
    }
	
	public JSONObject InsertData(String Id,String Password)
	{
	   Connection conn = null;
	   Statement stmt = null;

       try {
          conn = DriverManager.getConnection(DBConnectionInfo.JDBC_URL, DBConnectionInfo.USER, DBConnectionInfo.PASS);
		   String sql = String.format("insert into member(user_pass,id) values ('%s', '%s')", Password,Id);

  		  stmt = conn.createStatement();
          stmt.execute(sql);
		   return new JSONObject("{result:OK}");
       } catch (Exception ex) {
          System.out.println("Exception" + ex);
		   return new JSONObject("{result:Fail}");
       } finally {
		  if(stmt!=null) try{stmt.close();}catch(SQLException e){}
	      if(conn!=null) try{conn.close();}catch(SQLException e){}
       }
	}
}

