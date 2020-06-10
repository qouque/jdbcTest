package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC드라이버를 로딩하고 Connection객체를 생성하는 메서드 제공
 */
public class DBUtil {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			System.out.println("드라이버 로딩 완료!!!");
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "LHU";
			String password = "java";
			
			return DriverManager.getConnection(url, userId, password);
			
		}catch (SQLException e) {
			System.out.println("DB연결 실패");
			e.printStackTrace();
			return null;
		}
	}
	
	private static void disConnect(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null)try {rs.close();}catch (SQLException e2) {}
		if(stmt != null)try {stmt.close();}catch (SQLException e2) {}
		if(pstmt != null)try {pstmt.close();}catch (SQLException e2) {}
		if(conn != null)try {conn.close();}catch (SQLException e2) {}
		
	}
	
}













