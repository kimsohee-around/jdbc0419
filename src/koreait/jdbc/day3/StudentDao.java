package koreait.jdbc.day3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import koreait.jdbc.day2.OracleUtility;

//DAO : Data Access(접근-읽기와 쓰기) Object
//		ㄴ SQL 실행 메소드를 모아 놓은 클래스.
public class StudentDao {
	
	//나중에 db를 `쉽게 코딩`하기 위한 `프레임워크`를 사용하면 Exception 처리 안해도 됩니다.
	public int insert(StudentDto student) throws SQLException {
		
		Connection connection = OracleUtility.getConnection();
		
		String sql = "insert into TBL_STUDENT values(?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, student.getStuno());
		ps.setString(2, student.getName());
		ps.setInt(3, student.getAge());
		ps.setString(4, student.getAddress());
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result;
	}
	
	
	public int update(StudentDto student) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "update TBL_STUDENT set age = ? , address = ? where stuno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, student.getAge());
		ps.setString(2, student.getAddress());
		ps.setString(3, student.getStuno());
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		
		return result;
	}
	
	
	//delete 메소드는 여러분이 만들어보세요. - 매개변수는 ? 
	public int delete(String stuno) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "delete from TBL_STUDENT where stuno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, stuno);		//메소드 인자(매개변수)값을 sql 쿼리에 전달
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		
		return result;
	}
	
	
	//select * from TBL_STUDENT where stuno = '2023002'; 실행을 위한 
	//   ㄴ selectOne 메소드 정의
	public StudentDto selectOne(String stuno) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql ="select * from TBL_STUDENT where stuno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, stuno);
		
		ResultSet rs = ps.executeQuery();
		StudentDto result=null;
		if(rs.next()) {
			//String stuno2 = rs.getString(1);
			String name = rs.getString(2);
			int age = rs.getInt(3);
			String address = rs.getString(4);
			result = new StudentDto(stuno, name, age, address);
	// 코드 줄이면 아래와 같이 씁니다.		
	//		return  new StudentDto(stuno, rs.getString(2), rs.getInt(3), rs.getString(4));
		}
		return result;
	}
	
	
	

}
