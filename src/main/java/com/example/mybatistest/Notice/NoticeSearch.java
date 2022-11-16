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

@WebServlet(urlPatterns = "/Notice/Search")
public class NoticeSearch extends HttpServlet 
{
	private static final long serialVersionUID = 2L;
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://database-1.cuqcz3ydffen.ap-northeast-2.rds.amazonaws.com:3306/test";
	private final String USER = "admin";
	private final String PASS = "testadmindb";
 	
 	public NoticeSearch() 
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

		int ID = Integer.parseInt(request.getParameter("Id"));
		int subjectID = Integer.parseInt(request.getParameter("SubjectId"));
		String name = request.getParameter("Name");
		String content = request.getParameter("Content");

		Vector<NoticeBean> noticeList = SearchData(ID,subjectID,name,content);

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
			JSONArray j = new JSONArray();
			JSONObject resJson = new JSONObject();
			resJson.put("result", "FAIL");
			j.add(resJson);
			response.setContentType("application/json");
	        PrintWriter out = response.getWriter();
	        out.println(j.toString());
		}
    }
	
	public Vector<NoticeBean> SearchData(int id,int subjectid,String name,String content)
	{
	   Connection conn = null;
	   Statement stmt = null;
       ResultSet rs = null;
       Vector<NoticeBean> noticeList = new Vector<NoticeBean>();

       try 
       {
          String strQuery = "";
          String strCondition = "";
          
//          int id = Integer.parseInt(jsonData.get("ID").toString());
//          int subjectid = Integer.parseInt(jsonData.get("SubjectID").toString());
//          String name = jsonData.get("Name").toString();
//          String content = jsonData.get("Content").toString();
          boolean withContnet = false;
          
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

