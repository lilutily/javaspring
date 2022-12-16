-- hr이라는 아이디의 비밀번호를 hr로 변경하는 소스
alter user hr IDENTIFIED by hr;

-- 아이디랑 패스워드 만들어 주는 명령어
create user scott identified by  tiger default tablespace users TEMPORARY TABLESPACE temp;

-- 권한 부여
grant CONNECT, RESOURCE to scott;


-- 오늘 할 작업 새로운 아이디를 만들어서 사용할 예정
create user lilutily IDENTIFIED by 9902;
-- 권한 부여
grant connect, resource to lilutily;

-- 이쪽 pc가 아닌 오라클 서버 pc 경로로 해야됨
--create tablespace ts이름 datafile 'C:\\testsql\\test.dbf' size 600m;
create tablespace lilutily datafile 'C:\\testsql\\test.dbf' size 600m;

-- alter user 아이디 default tablespace ts이름; 
alter user lilutily default tablespace lilutily;