package com.example.mybatistest.Notice;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Vector;

@WebServlet(urlPatterns = "/Notice/Search2")
public class NoticeSearch2 extends HttpServlet
{
	private static final long serialVersionUID = 2L;
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://database-1.cuqcz3ydffen.ap-northeast-2.rds.amazonaws.com:3306/test";
	private final String USER = "admin";
	private final String PASS = "testadmindb";

 	public NoticeSearch2()
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

		ServletContext context = getServletContext( );
		//context.log(jsonBuffer.toString());
		
		JSONObject reqJson = new JSONObject();
		JSONParser parser = new JSONParser();


		try {
			reqJson = (JSONObject)parser.parse(jsonBuffer.toString());
		} catch(ParseException e) {
			System.out.println("변환에실패");
			e.printStackTrace();
		}


		//context.log(reqJson.get("SubjectID").toString());
		Vector<NoticeBean> noticeList = SearchData(reqJson);

		if(noticeList.size() > 0)
		{
			JSONArray jsonList = new JSONArray();

			for (int i = 0; i < noticeList.size(); i++)
			{
				NoticeBean noticeBean = noticeList.get(i);

				JSONObject jsonOb = new JSONObject();
			    jsonOb.put("ID", noticeBean.getID());
			    jsonOb.put("Subject", noticeBean.getID());
			    jsonOb.put("Name", noticeBean.getName());
			    jsonOb.put("Content", noticeBean.getContent());
			    jsonOb.put("SubjectName", noticeBean.getSubjectName());
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
	
	public Vector<NoticeBean> SearchData(JSONObject jsonData)
	{
	   Connection conn = null;
	   Statement stmt = null;
       ResultSet rs = null;
       Vector<NoticeBean> noticeList = new Vector<NoticeBean>();

       try 
       {
          String strQuery = "";
          String strCondition = "";
          
          int id = Integer.parseInt(jsonData.get("ID").toString());
          int subjectid = Integer.parseInt(jsonData.get("SubjectID").toString());
          String name = jsonData.get("Name").toString();
          String content = jsonData.get("Content").toString();
          boolean withContnet = false;

		   System.out.println("id = " + id);
		   System.out.println("subjectid = " + subjectid);
		   System.out.println("name = " + name);
		   System.out.println("content = " + content);

          conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
          
          // 공지사항 id가 -1이 아니면 컨텐츠 내용까지 조회
    	  if(id != -1)
    	  {
        	  strQuery = String.format("select a.*, b.Name from notice as a inner join subject as b on a.SubjectID = b.ID where a.ID = %d", id);
        	  if( subjectid != -1 )
            	  strCondition = String.format(" and a.SubjectID = %d", subjectid);

        	  if(!name.isEmpty())
       			  strCondition += String.format(" where a.Name like '%%%s%%'", name);

        	  if(!content.isEmpty())
       			  strCondition += String.format(" where a.Content like '%%%s%%'", content);

        	  strQuery += strCondition;
        	  withContnet = true;
    	  }
    	  else
    	  {
        	  strQuery = String.format("select ID, SubjectID, Name from notice");

        	  if( subjectid != -1 )
            	  strCondition = String.format(" where SubjectID = %d", subjectid);
    		  
        	  if(!name.isEmpty())
        	  {
        		  if(!strCondition.isEmpty()) 
        			  strCondition += String.format(" and Name like '%%%s%%'", name);
        		  else
        			  strCondition += String.format(" where Name like '%%%s%%'", name);
        	  }
        	  if(!content.isEmpty())
        	  {
        		  if(!strCondition.isEmpty()) 
        			  strCondition += String.format(" and Content like '%%%s%%'", content);
        		  else
        			  strCondition += String.format(" where Content like '%%%s%%'", content);
        	  }
        	  strQuery += strCondition;
    	  }
          ServletContext context = getServletContext();
  		  context.log(strQuery);

  		  stmt = conn.createStatement();
  		  rs = stmt.executeQuery(strQuery);
  		  
		  while (rs.next()) {
			  NoticeBean bean = new NoticeBean();
			  bean.setID(rs.getInt("ID"));
			  bean.setSubjectID(rs.getInt("SubjectID"));
			  bean.setName(rs.getString("Name"));
        	  if( withContnet )
        	  {
        		  bean.setContent(rs.getString("Content"));
            	  bean.setSubjectName(rs.getString("b.Name"));
        	  }
        	  else
        	  {
        		  bean.setContent("");
        		  bean.setSubjectName("");
        	  }
			  noticeList.addElement(bean);
	      }  		  
		  
       } catch (Exception ex) {
    	   System.out.println("Exception" + ex);
       } finally {
    	   if(rs!=null)   try{rs.close();}  catch(SQLException e){}
	   	   if(stmt!=null) try{stmt.close();}catch(SQLException e){}
	   	   if(conn!=null) try{conn.close();}catch(SQLException e){}
       }
       
       return noticeList;
	}
}

