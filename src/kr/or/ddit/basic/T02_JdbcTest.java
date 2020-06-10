package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Select 예제
 */
public class T02_JdbcTest {
/*
	문제 1) 사용자로부터 lprod_id값을 입력받아 입력한 값보다 lprod_id가 큰 자료들을 출력하시오
	
	문제 2) lprod_id값을 2개 입력받아서 두 값 중 작은 값부터 큰값 사이의 자료를 출력하시오.
 */
	Scanner sc = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		while(true) {
			
			System.out.println("1.문제 1 / 2.문제2 /  종료");
			System.out.print("입력 >> ");
			int a = Integer.parseInt(new T02_JdbcTest().sc.nextLine());
			
			if(a == 1) {
				new T02_JdbcTest().Exam01();
			}else if (a == 2) {
				new T02_JdbcTest().Exam02();
				
			}else {
				System.exit(0);
			}
		}
		
		
		
		
	}
	
	public void Exam01() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		System.out.print("lprod_id 입력 >> ");
		String lid = sc.nextLine();
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			
			// 2. DB에 접속 (Connection 객체 생성
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "LHU";
			String password = "java";
			
			conn = DriverManager.getConnection(url, userId, password);
			stmt = conn.createStatement();
			String sql = "select * from lprod where lprod_id > " + lid;
			rs = stmt.executeQuery(sql);
			
			System.out.println(" == 실행결과 == ");
			while(rs.next()) {
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("-------------------------------------");
			}
			System.out.println("출력완료");
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try {rs.close();}catch (SQLException e2) {}
			if(stmt != null)try {stmt.close();}catch (SQLException e2) {}
			if(conn != null)try {conn.close();}catch (SQLException e2) {}
		}
		
	}
	
	public void Exam02() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		System.out.print("lprod_id 입력 >> ");
		int lid = Integer.parseInt(sc.nextLine());
		System.out.print("lprod_id 입력 >> ");
		int lid2 = Integer.parseInt(sc.nextLine());
		
		int max = Math.max(lid, lid2);
		int min = Math.min(lid, lid2);
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			
			// 2. DB에 접속 (Connection 객체 생성
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "LHU";
			String password = "java";
			
			conn = DriverManager.getConnection(url, userId, password);
			stmt = conn.createStatement();
			String sql = "select * from lprod where lprod_id between " + min + "and " + max;
			rs = stmt.executeQuery(sql);
			
			System.out.println(" == 실행결과 == ");
			while(rs.next()) {
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("-------------------------------------");
			}
			System.out.println("출력완료");
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try {rs.close();}catch (SQLException e2) {}
			if(stmt != null)try {stmt.close();}catch (SQLException e2) {}
			if(conn != null)try {conn.close();}catch (SQLException e2) {}
		}
	}
}





















