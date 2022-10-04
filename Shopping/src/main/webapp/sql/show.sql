계정 : system계정으로 접속
혹시 비번을 까먹었다?
sqlplus sys/ as sysdba 로 접속

SQL> create tablespace shop
  2  datafile 'C:\oraclexe\app\oracle\oradata\XE\shop.dbf'
  3  size 10M;

Tablespace created.

SQL> create user client
  2  identified by 1234
  3  default tablespace shop
  4  quota unlimited on shop;

User created.

SQL> grant create table, create sequence to client;

Grant succeeded.

SQL> grant create session to client;

Grant succeeded.

SQL> conn client/1234

create sequence admin_seq
increment by 1 
start with 1;
/


--상위 카테고리
create table topcategory(
	topcategory_id number primary key,
	category_name varchar(30)
);

-- 하위 카테고리
create table subcategory(
	subcategory_id number primary key,
	category_name varchar(30),
	topcategory_id number,
	constraint fk_topcategory_subcategory foreign key (topcategory_id) 
	references topcategory(topcategory_id)
);

-- 상품 
create table product(
	product_id number primary key,
	product_name varchar(80),
	brand varchar(30),
	price number default 0,
	discount number default 0,
	memo varchar(1000),
	detail clob,
	product_img varchar(100),
	subcategory_id number,
	constraint fk_subcategory_product foreign key (subcategory_id) references subcategory(subcategory_id)
	);
	
create sequence seq_topcategory
increment by 1
start with 1;

create sequence seq_subcategory
increment by 1
start with 1;

create sequence seq_product
increment by 1
start with 1;


-- 회원 테이블
create table member(
	member_id number primary key,
	customer_id varchar(30),
	customer_name varchar(30),
	customer_pass varchar(30),
	customer_email varchar(50)
);

create sequence seq_member
increment by 1
start with 1;


-- 결제방법
create table paymethod(
	paymethod_id number primary key,
	payname varchar(30)
);

insert into paymethod(paymethod_id, payname) values(seq_paymethod.nextval, '카드결제');
insert into paymethod(paymethod_id, payname) values(seq_paymethod.nextval, '핸드폰');
insert into paymethod(paymethod_id, payname) values(seq_paymethod.nextval, '가상계좌');
insert into paymethod(paymethod_id, payname) values(seq_paymethod.nextval, '온라인입금');
COMMIT;

-- 주문 요약
create table ordersummary(
	ordersummary_id number primary key, 
	member_id number, -- 누가? fk (이미 member)
	paymethod_id number,  -- 어떤 방법으로 fk
	totalbuy number default 0, -- 얼마나 
	totalpay number default 0, -- 실제 결제금은?
	buydate date default sysdate, -- 언제?
	constraint fk_member_ordersummary foreign key(member_id) references member(member_id),
	constraint fk_paymethod_ordersummary foreign key(paymethod_id) references paymethod(paymethod_id)
);

-- 주문상세 (어떤 주문에 대한 상세정보)
create table orderdetail(
	orderdetail_id number primary key,
	product_id number,
	ea number default 0,
	ordersummary_id number,
	constraint fk_product_orderdetail foreign key(product_id) references product(product_id),
	constraint fk_ordersummary_orderdetail foreign key(ordersummary_id) references ordersummary(ordersummary_id)
);

create sequence seq_paymethod
increment by 1
start with 1;

create sequence seq_ordersummary
increment by 1
start with 1;

create sequence seq_orderdetail
increment by 1
start with 1;





