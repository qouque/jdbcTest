package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
	LPROD 테이블에 새로운 데이터 추가하기
	
	lprod_gu와 lprod_nm은 직접 입력받아 처리하고,
	lprod_id는 현재의 lprod_id들 중 제일 큰값보다 1 증가된 값으로 한다.
	(기타사항 : lprod_gu도 중복되는지 검사한다.)
 */
public class T04_JdbcTest {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		
		try {
			// 1. 드라이버 로딩
			/*Class.forName("oracle.jdbc.driver.OracleDriver"); 
									
			// 2. DB에 접속 (Connection 객체 생성
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "LHU";
			String password = "java";
			
			conn = DriverManager.getConnection(url, userId, password);
			*/
			
			conn = DBUtil.getConnection();
			
			//stmt = conn.createStatement();
			String lprod_gu = "";
			String lprod_nm = "";
			String sql ="";
			
			while(true) {
				System.out.print("lprod_gu 입력 >> ");
				lprod_gu = sc.nextLine();
				System.out.print("lprod_nm 입력 >> ");
				lprod_nm = sc.nextLine();
				sql = "select count(*) from lprod where lprod_gu = ?";
				
				pstmt = conn.prepareStatement("select count(*) from lprod where lprod_gu = lprod_gu");
				pstmt.setString(1, lprod_gu);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					if(rs.getInt(1) == 0) {
						break;
					}else {
						System.out.println("잘못 입력했습니다.");
						continue;
					}
				}
			}
			
			sc.close();
			
			sql = "select max(lprod_id) from lprod";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			int max = 0;
			if(rs.next()) {
				max = rs.getInt("max(lprod_id)");
			}
			max++;
			
			//System.out.println(max);
			
			sql = "insert into lprod "+ " (lprod_id, lprod_gu, lprod_nm) " + " values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setInt(1, max);
			pstmt.setString(2, lprod_gu);
			pstmt.setString(3, lprod_nm);
			
			int cnt = pstmt.executeUpdate();
			
			System.out.println("첫번쨰 실행 횟수 : " + cnt);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try {rs.close();}catch (SQLException e2) {}
			if(pstmt != null)try {pstmt.close();}catch (SQLException e2) {}
			if(conn != null)try {conn.close();}catch (SQLException e2) {}
		}
		
		
	}
	
}




















