-- step 1 : 테이블, 시퀀스 만들기

create table member_tbl_02 (
custno number(6) primary key,
custname varchar2(20),
phone varchar2(13),
address varchar2(60),
joindate date,
grade char(1),
city char(2));

create table money_tbl_02 (
custno number(6) not null,
salenol number(8) not null,
pcost number(8),
amount number(4),
price number(8),
pcode varchar2(4),
sdate date,
primary key(custno, salenol),
foreign key(custno) references member_tbl_02 (custno));


create sequence custno_seq start with 100001;
-- insert 구현 테스트 중에 시퀀스 값이 많이 증가됬다면 구현완료된 다음에
-- 시퀀스 삭제하고 새로 만든다.(값은 중복되지 않는 다음값으로 시작)
DROP SEQUENCE custno_seq;
create sequence custno_seq start with 100007;  -- 필요할 때. 가급적이면 오류 있을때는 drop 하고 초기부터 insert

-- step 2 : 참조 테이블 먼저 insert 하기
DELETE FROM MEMBER_TBL_02 mt ;
DELETE FROM MONEY_TBL_02 mt ;
insert into member_tbl_02 values
	(custno_seq.nextval, '김행복', '010-1111-2222', '서울 동대문구 휘경1동', '2015-12-02', 'A', '01');
insert into member_tbl_02 values
	(custno_seq.nextval, '이축복', '010-1111-3333', '서울 동대문구 휘경2동', '2015-12-06', 'B', '01');
insert into member_tbl_02 values
	(custno_seq.nextval, '장믿음', '010-1111-4444', '울릉군 울릉읍 독도1리', '2015-10-01', 'B', '30');
insert into member_tbl_02 values
	(custno_seq.nextval, '최사랑', '010-1111-5555', '울릉군 울릉읍 독도2리', '2015-11-13', 'A', '30');
insert into member_tbl_02 values
	(custno_seq.nextval, '진평화', '010-1111-6666', '제주도 제주시 외나무골', '2015-12-25', 'B', '60');
insert into member_tbl_02 values
	(custno_seq.nextval, '차공단', '010-1111-7777', '제주도 제주시 감나무골', '2015-12-11', 'C', '60');

-- step 3 : 참조하는 테이블(외래키가 있는 테이블) insert 하기

insert into money_tbl_02 values
	(100001, 20160001, 500, 5, 2500, 'A001', '2016-01-01');
insert into money_tbl_02 values
	(100001, 20160002, 1000, 4, 4000, 'A002', '2016-01-01');
insert into money_tbl_02 values
	(100001, 20160003, 500, 3, 1500, 'A008', '2016-01-01');
insert into money_tbl_02 values
	(100002, 20160004, 2000, 1, 2000, 'A004', '2016-01-02');
insert into money_tbl_02 values
	(100002, 20160005, 500, 1, 500, 'A001', '2016-01-03');
insert into money_tbl_02 values
	(100003, 20160006, 1500, 2, 3000, 'A003', '2016-01-03');
insert into money_tbl_02 values
	(100004, 20160007, 500, 2, 1000, 'A001', '2016-01-04');
insert into money_tbl_02 values
	(100004, 20160008, 300, 1, 300, 'A005', '2016-01-04');
insert into money_tbl_02 values
	(100004, 20160009, 600, 1, 600, 'A006', '2016-01-04');
insert into money_tbl_02 values
	(100004, 20160010, 3000, 1, 3000, 'A007', '2016-01-06');

-- insert 결과 확인하기

select * from member_tbl_02;
select * from money_tbl_02;

-- 회원매출조회 join과 group by 하기

-- step 1 회원별 매출합계
select custno, sum(price) from money_tbl_02 group by custno;

-- step 2 정렬 기준 확인하기
select custno, sum(price) from money_tbl_02 group by custno order by sum(price) desc;

-- step 3 custno 컬럼으로 조인하여 고객 정보 전체 가져오기
select * from member_tbl_02 met,
	(select custno, sum(price) asum from money_tbl_02 mot 
	group by custno
	order by asum desc) sale
where met.custno = sale.custno;
-- 또는
select * from member_tbl_02 met join
	(select custno, sum(price) asum from money_tbl_02 mot 
	group by custno
	order by asum desc) sale
on met.custno = sale.custno;

-- step 4 필요한 컬럼만 가져오기
select met.custno, custname,
	decode(met.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') as grade,
	asum
	from member_tbl_02 met join
	(select custno, sum(price) asum from money_tbl_02 mot 
	group by custno
	order by asum desc) sale
	on met.custno = sale.custno ORDER BY asum desc;
-- 또는
select met.custno, custname,
	decode(met.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') as grade,
	sale.asum
	from member_tbl_02 met,
	(select custno, sum(price) asum from money_tbl_02 mot 
	group by custno
	order by asum desc) sale
	where met.custno = sale.custno 
	ORDER BY total desc;

++ decode(grade, 'A', 'VIP', 'B', '일반', 'C', '직원');

-- 외부조인 : 매출이 없는 회원도 포함한다.
select met.custno, custname,
	decode(met.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') as grade,
	nvl(sale.asum,0) total
	from member_tbl_02 met LEFT OUTER join
	(select custno, sum(price) asum from money_tbl_02 mot 
	group by custno
	order by asum desc) sale
	on met.custno = sale.custno ORDER BY total DESC ,custno;
	
	