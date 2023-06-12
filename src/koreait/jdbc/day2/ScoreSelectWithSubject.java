package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ScoreSelectWithSubject {

	public static void main(String[] args) {
		Connection conn = OracleUtility.getConnection();
		System.out.println(":::::::::: 성적 조회 메뉴 :::::::::::");
		System.out.println("과목명을 입력하여 조회 합니다.");
		selectManyScore(conn);		
		
		OracleUtility.close(conn);
	}

	private static void selectCount(Connection conn, String subject) {
		String sql="select count(*) \r\n"
				+ "from TBL_SCORE \r\n"
				+ "where subject = ?";
		//count 와 같은 함수 결과는 행 1개 , 컬럼 1개
		try(
				PreparedStatement ps = conn.prepareStatement(sql);
			){
				ps.setString(1, subject);	
				
				ResultSet rs =ps.executeQuery();
				int count=0;
				if(rs.next()) {
					count = rs.getInt(1);
				}
				//참고 :  입력한 과목의 건(행) 수를 조회할수 있습니다.
				System.out.println("과목 << " + subject + " >> " + count + " 건이 조회 되었습니다." );
			}
			catch (SQLException e) {
				System.out.println("데이터 조회에 문제가 생겼습니다. 상세내용 -" + e.getMessage());
				//결과 집합을 모두 소모했음 -> 조회 결과가 없는데 rs.getXXXX 메소드 실행할 때의 예외 메시지.
			}
		
	}

	private static void selectManyScore(Connection conn) {
		Scanner sc = new Scanner(System.in);
		String sql="select stuno, jumsu, term ,teacher \r\n"
				+ "from TBL_SCORE \r\n"
				+ "where subject = ?";
		System.out.print("조회할 과목명 입력 >>> ");
		String subject = sc.nextLine();
		
		try(
			PreparedStatement ps = conn.prepareStatement(sql);
		){
			ps.setString(1, subject);	
			
			ResultSet rs =ps.executeQuery();	
			//int count=0;
			while(rs.next()) {  //주의 : 테이블 컬럼의 구조를 알아야 인덱스를 정할 수 있습니다.
				System.out.println(String.format("%10s %10d %10s %20s", 
							rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4)
						));
			//	count++;
			}
			selectCount(conn, subject);
		}
		catch (SQLException e) {
			System.out.println("데이터 조회에 문제가 생겼습니다. 상세내용 -" + e.getMessage());
			//결과 집합을 모두 소모했음 -> 조회 결과가 없는데 rs.getXXXX 메소드 실행할 때의 예외 메시지.
		}
		sc.close();

	}
}
/*
	모든 학생 조회하는 StudentSelectAllMenu 클래스 : 1줄에 1개 행을 출력하세요.
	과목명을 입력하면 해당 과목 조회하는 ScoreSelectWithSubject 클래스 
			ㄴ 조회 내용은 모든 컬럼.

*/







