package koreait.jdbc.day3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import koreait.jdbc.day2.OracleUtility;

//DAO : Data Access(접근-읽기와 쓰기) Object
//		ㄴ SQL 실행 메소드를 모아 놓은 클래스.
public class StudentDao {
	
	public int insertOne(StudentDto student) throws SQLException {
		
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
		OracleUtility.close(connection);
		
		return result;
	}
	
	
	//delete 메소드는 여러분이 만들어보세요. - 매개변수는 ? 
	

}
