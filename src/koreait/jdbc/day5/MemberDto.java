package koreait.jdbc.day5;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
@Getter
@AllArgsConstructor
@ToString
@Builder
public class MemberDto {
	
	private int custno;
	private String custname;
	private String phone;
	private String address;
	private Date joindate;
	private String grade;
	private String city;
}
