package com.example.mybatistest.Notice;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/TestServlet")
public class TestSurvlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	@Override
    public void service(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>TestServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>This is First Servlet Application!!!</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}