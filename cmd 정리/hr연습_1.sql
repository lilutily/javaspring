SELECT*FROM employees;
-- 사원번호가 176인 사람의 LAST_NAME과 DEPARTMENT_ID
select last_name, department_id from employees where employee_id=176;

-- SALARY 가 1200이상되는 직원들의 LAST_NAME과 SALARY 조회
select last_name,salary from employees where salary>=1200;

-- SALARY가 5000~12000의 범위 이외인 직원들의 LAST_NAME과 SALARY 조회
select last_name,salary from employees where salary>12000 or salary<5000;

-- 20번 및 50번 부서에서 근무하는 모든사원들의 last_name 및 department_id를 조회
select  last_name, department_id from employees where department_id in(20, 50);

-- 2008/02/20 ~ 2008/05/01 사이에 고용되어 사원들의 last_name, employee_id, hire_Date를 조회 단 hire_date 내림차순 조회
select last_name, employee_id, hire_date from employees where hire_date >= '2008/02/20' and hire_date <= '2008/05/01' order by hire_date desc;
-- 2004년도에 고용된 모든 사람들의 last_name,hire_Date 조회 hire_Date 오름차순
select last_name, hire_date from employees where hire_date >='2004/01/01' and hire_date <='2004/12/31' order by hire_date;

-- salary가 5000~12000의 범위 이외인 사원들의 last_name, salary 조회
select last_name, salary from employees where salary not BETWEEN 5000 and 12000;
-- 20번 및 50번 부서에서 근무하는 salary가 5000~12000사이인 사원들의 last_name 및 department_id 조회
select last_name, department_id from employees where department_id in(20,50) and salary BETWEEN 5000 and 12000;
-- 2008/02/20~2008/05/01사이에 고용된 사원들의 last_name, employee_id, hire_date 조회 단 hire date 내림차순 조회
select last_name, employee_id, hire_date from employees where hire_date BETWEEN '2008/02/20' and '2008/05/01' order by hire_date desc;   
-- 2004년도에 고용된 모든 사람들의 last_name, hire_date 조회 단 hire_date 오름차순 정렬
select last_name, hire_date from employees where hire_date BETWEEN '2004/01/01' and '2004/12/31' order by hire_date;

-- LAST_NAME에 U가 포함되는 사원들의 사번 및 LAST_NAME 조회
select last_name, employee_id from employees where last_name like '%u%';
-- LAST_NAME의 4번째 글자가 A인 사원들의 LAST_NAME 조회
SELECT last_name FROM employees WHERE LAST_NAME LIKE '___a%';
-- LAST_NAME에 글자가 A혹은 E글자가 들어있는 사원들의 LAST_NAME 조회 후 LAST_NAME 오름차순
SELECT last_name FROM employees WHERE last_name like '%a%' OR last_name like '%e%' order by LAST_NAME asc;

--manager_id가 없는 사원들의 last_name, job_id 조회
select last_name, job_id from employees where manage_id is null;
-- job_id가 ST_CLERK 사원의 department_id 조회(단, 부서번호가 null인 것은 제외) 
select department_id from employees where job_id = 'ST_CLERK' and department_id is not null;
-- commission_pct가 null이 아닌 사원들 중에서 salary * commission_pct를 구한후 employee_id, first_name, job_id, commission 조회
select  employee_id, first_name, job_id, commission_pct, salary*commission_pct as 값 from employees where salary*commission_pct is not null;