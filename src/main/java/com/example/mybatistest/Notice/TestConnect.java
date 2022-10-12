package com.example.mybatistest.Notice;

import java.sql.*;

public class TestConnect{
	public static void main(String args[]){
		Connection con;

		try{
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			con=DriverManager.getConnection("jdbc:mariadb://localhost:3306/notice", "root", "p12345");
			System.out.println("Success");
		}
		catch(SQLException ex){ System.out.println("SQLException" + ex);}
		catch(Exception ex){ System.out.println("Exception:" + ex);}
	}
}
