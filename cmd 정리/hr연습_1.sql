SELECT*FROM employees;
-- �����ȣ�� 176�� ����� LAST_NAME�� DEPARTMENT_ID
select last_name, department_id from employees where employee_id=176;

-- SALARY �� 1200�̻�Ǵ� �������� LAST_NAME�� SALARY ��ȸ
select last_name,salary from employees where salary>=1200;

-- SALARY�� 5000~12000�� ���� �̿��� �������� LAST_NAME�� SALARY ��ȸ
select last_name,salary from employees where salary>12000 or salary<5000;

-- 20�� �� 50�� �μ����� �ٹ��ϴ� ��������� last_name �� department_id�� ��ȸ
select  last_name, department_id from employees where department_id in(20, 50);

-- 2008/02/20 ~ 2008/05/01 ���̿� ���Ǿ� ������� last_name, employee_id, hire_Date�� ��ȸ �� hire_date �������� ��ȸ
select last_name, employee_id, hire_date from employees where hire_date >= '2008/02/20' and hire_date <= '2008/05/01' order by hire_date desc;
-- 2004�⵵�� ���� ��� ������� last_name,hire_Date ��ȸ hire_Date ��������
select last_name, hire_date from employees where hire_date >='2004/01/01' and hire_date <='2004/12/31' order by hire_date;

-- salary�� 5000~12000�� ���� �̿��� ������� last_name, salary ��ȸ
select last_name, salary from employees where salary not BETWEEN 5000 and 12000;
-- 20�� �� 50�� �μ����� �ٹ��ϴ� salary�� 5000~12000������ ������� last_name �� department_id ��ȸ
select last_name, department_id from employees where department_id in(20,50) and salary BETWEEN 5000 and 12000;
-- 2008/02/20~2008/05/01���̿� ���� ������� last_name, employee_id, hire_date ��ȸ �� hire date �������� ��ȸ
select last_name, employee_id, hire_date from employees where hire_date BETWEEN '2008/02/20' and '2008/05/01' order by hire_date desc;   
-- 2004�⵵�� ���� ��� ������� last_name, hire_date ��ȸ �� hire_date �������� ����
select last_name, hire_date from employees where hire_date BETWEEN '2004/01/01' and '2004/12/31' order by hire_date;

-- LAST_NAME�� U�� ���ԵǴ� ������� ��� �� LAST_NAME ��ȸ
select last_name, employee_id from employees where last_name like '%u%';
-- LAST_NAME�� 4��° ���ڰ� A�� ������� LAST_NAME ��ȸ
SELECT last_name FROM employees WHERE LAST_NAME LIKE '___a%';
-- LAST_NAME�� ���ڰ� AȤ�� E���ڰ� ����ִ� ������� LAST_NAME ��ȸ �� LAST_NAME ��������
SELECT last_name FROM employees WHERE last_name like '%a%' OR last_name like '%e%' order by LAST_NAME asc;

--manager_id�� ���� ������� last_name, job_id ��ȸ
select last_name, job_id from employees where manage_id is null;
-- job_id�� ST_CLERK ����� department_id ��ȸ(��, �μ���ȣ�� null�� ���� ����) 
select department_id from employees where job_id = 'ST_CLERK' and department_id is not null;
-- commission_pct�� null�� �ƴ� ����� �߿��� salary * commission_pct�� ������ employee_id, first_name, job_id, commission ��ȸ
select  employee_id, first_name, job_id, commission_pct, salary*commission_pct as �� from employees where salary*commission_pct is not null;