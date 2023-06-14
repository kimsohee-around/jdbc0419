package koreait.jdbc.day4;

import java.util.List;

public class JBuyDao {		//구매와 관련된 CRUD 실행 SQL. DAO : JCustomerDao , JProductDao
//메소드 이름은 insert , update , delete , select , selectByPname 등등으로 이름을 작성하세요.

		public int insertMany(List<JBuy> carts) {
		//5. 상품 구매(결제)하기 - 장바구니의 데이터를 `구매` 테이블에 입력하기 (여러개 insert)	
			String sql = "values (seq.nextval , ? ,?,?,sysdate)";
//			
//			for(JBuy b : carts) {
//				ps.setString(1,b.getCustomid());
//				ps.setString(2,b.getCustomid());
//				ps.setString(3,b.getCustomid());
//				ps.executeUpdate();
//			}
			return 0;
		}
	
}
