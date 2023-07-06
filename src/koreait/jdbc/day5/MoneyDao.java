package koreait.jdbc.day5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;

// select 회원 매출 조회
public class MoneyDao {
   
   public List<MoneyDto> moneyall() throws SQLException {
      Connection conn = OracleUtility.getConnection();
      String sql = "SELECT mt.custno,custname,"
      			+ "	decode(grade,'A','VIP','B','일반','C','직원') agrade,psum \r\n"
				+ "FROM MEMBER_TBL_02 mt\r\n"
				+ "JOIN  \r\n"
				+ "(	 \r\n"
				+ "	SELECT custno , sum(price) psum     \r\n"
				+ "	FROM MONEY_TBL_02 \r\n"
				+ "	GROUP BY custno\r\n"
				+ ") sale  \r\n"
				+ "ON mt.custno = sale.custno\r\n"
				+ "ORDER BY psum desc";
      PreparedStatement ps = conn.prepareStatement(sql);
      
      List<MoneyDto> result = new ArrayList<>();
      ResultSet rs = ps.executeQuery();
      while(rs.next()) {
         MoneyDto total = new MoneyDto(rs.getInt(1), 
                              rs.getString(2), 
                              rs.getString(3), 
                              rs.getInt(4));
         result.add(total);
      }
      return result;
   }
   
}