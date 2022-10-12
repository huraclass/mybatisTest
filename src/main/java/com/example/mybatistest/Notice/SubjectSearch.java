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

@WebServlet(urlPatterns = "/Subject/Search")
public class SubjectSearch extends HttpServlet 
{
	private static final long serialVersionUID = 2L;
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://database-1.cuqcz3ydffen.ap-northeast-2.rds.amazonaws.com:3306/test";
	private final String USER = "admin";
	private final String PASS = "testadmindb";
 	
 	public SubjectSearch() 
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

//		StringBuffer jsonBuffer = new StringBuffer();
//		String strLine = null;
//
//		BufferedReader reader = request.getReader();
//		while ((strLine = reader.readLine()) != null)
//			jsonBuffer.append(strLine);
//
//		ServletContext context = getServletContext( );
//		context.log(jsonBuffer.toString());
//
//		JSONObject reqJson = new JSONObject();
//		JSONParser parser = new JSONParser();
//
//		try {
//			reqJson = (JSONObject)parser.parse(jsonBuffer.toString());
//		} catch(ParseException e) {
//			System.out.println("변환에 실패");
//			e.printStackTrace();
//		}
			
		//context.log(reqJson.get("ID").toString());
		Vector<SubjectBean> subjectList = SearchData(id);

		if(subjectList.size() > 0)
		{
			JSONArray jsonList = new JSONArray();

			for (int i = 0; i < subjectList.size(); i++)
			{
				SubjectBean subjectBean = subjectList.get(i);

				JSONObject jsonOb = new JSONObject();
			    jsonOb.put("ID", subjectBean.getID());
			    jsonOb.put("Name", subjectBean.getName());
			    jsonList.add(jsonOb);
			}

			response.setContentType("application/json;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println(jsonList.toString());
		}
		else
		{
			JSONObject resJson = new JSONObject();
			resJson.put("result", "FAIL");

			response.setContentType("application/json");
	        PrintWriter out = response.getWriter();
	        out.println(resJson.toString());
		}
    }
	
	public Vector<SubjectBean> SearchData(int id)
	{
	   Connection conn = null;
	   Statement stmt = null;
       ResultSet rs = null;
       Vector<SubjectBean> subJectList = new Vector<SubjectBean>();

       try 
       {
          String strQuery;
          conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
          if( id == -1 )
          {
        	  strQuery = "select * from subject";
          }
          else
          {
        	  strQuery = String.format("select * from subject where ID = %d", id);
          }
          ServletContext context = getServletContext( );
  		  context.log(strQuery);

  		  stmt = conn.createStatement();
  		  rs = stmt.executeQuery(strQuery);
  		  
		  while (rs.next()) {
			  SubjectBean bean = new SubjectBean();
			  bean.setID(rs.getInt("ID"));
			  bean.setName(rs.getString("Name"));
			  subJectList.addElement(bean);
	      }  		  
		  
       } catch (Exception ex) {
    	   System.out.println("Exception" + ex);
       } finally {
    	   if(rs!=null)   try{rs.close();}  catch(SQLException e){}
	   	   if(stmt!=null) try{stmt.close();}catch(SQLException e){}
	   	   if(conn!=null) try{conn.close();}catch(SQLException e){}
       }
       
       return subJectList;
	}
}

