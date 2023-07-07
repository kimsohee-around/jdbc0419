package koreait.jdbc.day6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import koreait.jdbc.day2.OracleUtility;
import koreait.jdbc.day4.JCustomer;

public class JCustomerDao2 {
	private static JCustomerDao2 dao = new JCustomerDao2();
	private JCustomerDao2() {}
	public static JCustomerDao2 getJCustomerDao2() {
		return dao;
	}

	public JCustomer login(String id,String password) throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "select custom_id ,name  "
				+ "from j_custom where custom_id =? and password=?";
		JCustomer result = null;
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2,password);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			result = JCustomer.builder()
					.custom_id(rs.getString(1))
					.name(rs.getString(2))
					.build();
		}
		
		return result;		//result 가 null 이 아니면 로그인 성공
	}
}
//LogingMain 에서 사용자에게 아이디 >>> , 패스워드 >>>>  
//로그인 처리 결과 '로그인 성공했습니다.xxx 님 환영합니다.' 또는 '입력한 계정정보가 틀립니다.'출력