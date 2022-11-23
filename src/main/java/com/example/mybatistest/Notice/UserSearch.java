package com.example.mybatistest.Notice;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import lombok.extern.log4j.Log4j2;
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
@Log4j2
@WebServlet(urlPatterns = "/User/Search")
public class UserSearch extends HttpServlet 
{
	private static final long serialVersionUID = 2L;
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://database-1.cuqcz3ydffen.ap-northeast-2.rds.amazonaws.com:3306/test";
	private final String USER = "admin";
	private final String PASS = "testadmindb";
 	
 	public UserSearch() 
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
		String ID = request.getParameter("Id");
		String Password = request.getParameter("Password");
		log.info("Id" + ID);
		JSONObject resJson = new JSONObject();
		JSONArray j;
		j = SearchData(ID, Password);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
        out.println(j.toString());
    }
	
	public JSONArray SearchData(String Id,String Password)
	{
	   Connection conn = null;
	   Statement stmt = null;
	   ResultSet rs = null;
	   JSONObject resJson = new JSONObject();
	   JSONArray j = new JSONArray();
	   int userCode = 0;
	   int grade = 0;
//       Id = jsonData.get("Id").toString();
//       Password = jsonData.get("Password").toString();

//select member.user_code
//from member left join user on member.user_name = user.nickname
//where user.id = 'test' and user.password = '1234'
       try {
          conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
          String strQuery = String.format("select user_code , grade from member where id = '%s' and user_pass = '%s'", Id, Password);
          //ServletContext context = getServletContext( );
  		  //context.log(strQuery);

  		  stmt = conn.createStatement();
          rs = stmt.executeQuery(strQuery);
          
          int count = 0;
          while(rs.next()){
        	  count++;
			  userCode = rs.getInt(1);
			  grade = rs.getInt(2);
          }
          if(count == 0) {
			  resJson.put("result", "Fail");
          }
		  else{
			  resJson.put("result", "OK");
			  resJson.put("userCode", userCode);
			  resJson.put("grade", grade);
		  }
		   j.add(resJson);

          return j;
       } catch (Exception e) {
		   e.printStackTrace();
       } finally {
		  if(stmt!=null) try{stmt.close();}catch(SQLException e){}
	      if(conn!=null) try{conn.close();}catch(SQLException e){}
       }
	   return null;
	}
}

