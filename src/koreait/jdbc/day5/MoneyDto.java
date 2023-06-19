package koreait.jdbc.day5;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class MoneyDto {
   
   int custno;
   String custname;
   String grade;
   int total;
   
}