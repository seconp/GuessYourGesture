package com.qf.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SQLConnection {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/app";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";
	private static final String PATH = "com.mysql.jdbc.Driver";
	private static Connection conn;
	private static PreparedStatement ps;
	
	public static PreparedStatement openConn(String sql){
		try {
			Class.forName(PATH);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			ps = conn.prepareStatement(sql);
			return ps;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void closeConn(){
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
