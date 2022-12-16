-- SQL은 대소문자 구별안함 단 데이터들의 대소문자는 구별함

 

-- scott 계정에 존재하는 모든 테이블 조회

select * from tab;

 

-- scott 계정에 존재하는 emp 테이블의 모든 정보를 조회

select * from emp;

 

-- emp 테이블의 empno ename,job만 조회

  SELECT empno, ename, job from emp;

  

-- emp 테이블의 empno, deptno 조회

select empno, deptno from emp;

 

-- emp 테이블의 deptno 조회

select deptno from emp;

 

--중복값을 제거하기 위해 사용 : distinct

select DISTINCT deptno from emp;

 

--별칭을 붙여서 조회하고 싶을때 : as

select empno as 사원번호 from emp;

 

--특정 부분을 요구할때 띄어쓰기 하고 싶으면 " "를 사용

select ename, sal, job as "직 책", comm, sal*12+comm as 연봉 from emp;

 

--정렬 : order by (오름차순(asc), 내림차순(desc)) 컬럼명

 

-- ename, sal 조회할때 내침차순으로 정렬 asc : 오름차순

select ename,sal from emp order by sal asc;

select ename,sal from emp order by sal desc;

 

--오름차순 출력할때 asc(생략가능  = 기본값);

select ename,sal from emp order by sal;

 

--emp 테이블의 전체내용을 조회할때 사원번호 내림차순으로 출력하시오.

select * from emp order by empno desc;

 

--emp 테이블을 전체 조회할때 (부서번호는 오름차순, 부서번호가 같은 것들은 급여를 내림차순으로 ) 출력하고 싶을때

select * from emp order by deptno asc, sal desc;

select * from emp order by deptno, sal desc;

 

select empno as 사원번호, ename as 이름, mgr as 매니저, sal as 월급, comm as "보 너스", deptno as 부서번호 from emp order by deptno desc, ename asc;

 

--특정 조건을 기준으로 원하는 데이터를 조회하고 싶을대 : where

--전체 데이터를 조회, 단 부서번호가 30번인 데이터만

select * from emp where deptno = 30;

 

--사원번호가 7782인 사람들의 전체 조회

select * from emp where empno = 7782;

 

--부서 번호가 30번이고, job이 salesman 인 사람 조회 //AS 같이 띄어쓰기를 사용하는 상황은 " "; 연속사용시 and 문자열(대소문자 확인)은 ' ' 사용 

select * from emp where deptno = 30 and job = 'SALESMAN';

 

--사원번호가 7499이고(and) 부서번호가 30인 사람 조회

select * from emp where empno = 7499 and deptno = 30;

 

--부서번호가 30이거나(or) 사원직책이 clerk인 사원전체 조회

select * from emp where deptno = 30 or job = 'CLERK';

 

--산술연산자를 사용한 where

 

--연봉이 36000인 사원 조회

select * from emp where sal*12 = 36000;

 

--월급이 3000보다 큰 사원 조회

select * from emp where sal > 3000;

--월급이 3000이상인 사원 조회

 

select * from emp where sal >=3000;

 

--급여가 2500 이상이고 직업이 analyst인 사원 조회

select * from emp where sal >=2500 and job = 'ANALYST';

 

--직무가 SALESMAN 이거나, CLERK 이거나 MANAGER인 사원 조회 

select * from emp where job = 'SALESMAN' or job = 'CLERK' or job = 'MANAGER';

 

--등가비교연산자 (같다 =,같지않다 > !=, <>, ^=, not)

select * from emp where sal != 3000;

select * from emp where sal <> 3000;

select * from emp where sal ^= 3000;

select * from emp where not sal = 3000; -- not satl = ? 형태

 

select * from emp where job in('SALESMAN', 'CLERK', 'MANAGER'); --IN 안에 저중에 있는것들을 출력함 OR 과 같음

select * from emp where job in('SALESMAN');

select * from emp where job not in('SALESMAN', 'CLERK', 'MANAGER'); -- in 안에 있는 값을 제외한 것을 출력

 

--부서 번호가 10이거나 20번인 사원 조회

select * from emp where deptno in (10,20);

 

--급여가 2000~3000 사이의 사원 전체 조회 (사이이면 between)

select * from emp where sal BETWEEN 2000 and 3000;

 

--r급여가 2000~ 3000 사이가 아닌 사원

select * from emp where sal not  BETWEEN 2000 and 3000;

 

 

select * from emp;

--LIKE 연산자와 와일드카드

--일부 문자열이 포함된 데이터 조회

--와일드 카드 : % // 길이와 상관없이 모든 문자 데이터를 의미

--사원명이 s로 시작하는 사원조회 

Select * from emp where ename like '%S';

--사원명의 두번째 글자가 L인 사원 조회

Select * from emp where ename like '_L';

Select * from emp where ename like '_L%';

 

--사원명이 am문자가 포함되어있는 사원 조회

Select * from emp where ename like '%AM%';

 

--사원명이 AM문자가 포함되어 있지 않은 사원

Select * from emp where ename not like '%AM%';

 

--넣어져있는 문자열에서 (_)나 (%)를 찾을때는 ' '안에 '\_'이나, '\%'를 넣어서 찾는다.

 

--is null : 0이 아니고 완전히 비어있는 상태를 의미, 연산자 사용 불가

select * from emp;

select * from emp where comm=null; --실행 안됨

 

--원하는 밸류에 null값들을 불러 올때 is null 사용

select * from emp where comm is null;

 

--mgr이 null인 사원 조회

select * from emp where mgr is null;

 

--mgr이 null이 아닌 사원 조회

select * from emp where mgr is not null; --not을 사용시 is와 null 사이에 넣어야한다.