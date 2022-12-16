-- SQL�� ��ҹ��� �������� �� �����͵��� ��ҹ��ڴ� ������

 

-- scott ������ �����ϴ� ��� ���̺� ��ȸ

select * from tab;

 

-- scott ������ �����ϴ� emp ���̺��� ��� ������ ��ȸ

select * from emp;

 

-- emp ���̺��� empno ename,job�� ��ȸ

  SELECT empno, ename, job from emp;

  

-- emp ���̺��� empno, deptno ��ȸ

select empno, deptno from emp;

 

-- emp ���̺��� deptno ��ȸ

select deptno from emp;

 

--�ߺ����� �����ϱ� ���� ��� : distinct

select DISTINCT deptno from emp;

 

--��Ī�� �ٿ��� ��ȸ�ϰ� ������ : as

select empno as �����ȣ from emp;

 

--Ư�� �κ��� �䱸�Ҷ� ���� �ϰ� ������ " "�� ���

select ename, sal, job as "�� å", comm, sal*12+comm as ���� from emp;

 

--���� : order by (��������(asc), ��������(desc)) �÷���

 

-- ename, sal ��ȸ�Ҷ� ��ħ�������� ���� asc : ��������

select ename,sal from emp order by sal asc;

select ename,sal from emp order by sal desc;

 

--�������� ����Ҷ� asc(��������  = �⺻��);

select ename,sal from emp order by sal;

 

--emp ���̺��� ��ü������ ��ȸ�Ҷ� �����ȣ ������������ ����Ͻÿ�.

select * from emp order by empno desc;

 

--emp ���̺��� ��ü ��ȸ�Ҷ� (�μ���ȣ�� ��������, �μ���ȣ�� ���� �͵��� �޿��� ������������ ) ����ϰ� ������

select * from emp order by deptno asc, sal desc;

select * from emp order by deptno, sal desc;

 

select empno as �����ȣ, ename as �̸�, mgr as �Ŵ���, sal as ����, comm as "�� �ʽ�", deptno as �μ���ȣ from emp order by deptno desc, ename asc;

 

--Ư�� ������ �������� ���ϴ� �����͸� ��ȸ�ϰ� ������ : where

--��ü �����͸� ��ȸ, �� �μ���ȣ�� 30���� �����͸�

select * from emp where deptno = 30;

 

--�����ȣ�� 7782�� ������� ��ü ��ȸ

select * from emp where empno = 7782;

 

--�μ� ��ȣ�� 30���̰�, job�� salesman �� ��� ��ȸ //AS ���� ���⸦ ����ϴ� ��Ȳ�� " "; ���ӻ��� and ���ڿ�(��ҹ��� Ȯ��)�� ' ' ��� 

select * from emp where deptno = 30 and job = 'SALESMAN';

 

--�����ȣ�� 7499�̰�(and) �μ���ȣ�� 30�� ��� ��ȸ

select * from emp where empno = 7499 and deptno = 30;

 

--�μ���ȣ�� 30�̰ų�(or) �����å�� clerk�� �����ü ��ȸ

select * from emp where deptno = 30 or job = 'CLERK';

 

--��������ڸ� ����� where

 

--������ 36000�� ��� ��ȸ

select * from emp where sal*12 = 36000;

 

--������ 3000���� ū ��� ��ȸ

select * from emp where sal > 3000;

--������ 3000�̻��� ��� ��ȸ

 

select * from emp where sal >=3000;

 

--�޿��� 2500 �̻��̰� ������ analyst�� ��� ��ȸ

select * from emp where sal >=2500 and job = 'ANALYST';

 

--������ SALESMAN �̰ų�, CLERK �̰ų� MANAGER�� ��� ��ȸ 

select * from emp where job = 'SALESMAN' or job = 'CLERK' or job = 'MANAGER';

 

--��񱳿����� (���� =,�����ʴ� > !=, <>, ^=, not)

select * from emp where sal != 3000;

select * from emp where sal <> 3000;

select * from emp where sal ^= 3000;

select * from emp where not sal = 3000; -- not satl = ? ����

 

select * from emp where job in('SALESMAN', 'CLERK', 'MANAGER'); --IN �ȿ� ���߿� �ִ°͵��� ����� OR �� ����

select * from emp where job in('SALESMAN');

select * from emp where job not in('SALESMAN', 'CLERK', 'MANAGER'); -- in �ȿ� �ִ� ���� ������ ���� ���

 

--�μ� ��ȣ�� 10�̰ų� 20���� ��� ��ȸ

select * from emp where deptno in (10,20);

 

--�޿��� 2000~3000 ������ ��� ��ü ��ȸ (�����̸� between)

select * from emp where sal BETWEEN 2000 and 3000;

 

--r�޿��� 2000~ 3000 ���̰� �ƴ� ���

select * from emp where sal not  BETWEEN 2000 and 3000;

 

 

select * from emp;

--LIKE �����ڿ� ���ϵ�ī��

--�Ϻ� ���ڿ��� ���Ե� ������ ��ȸ

--���ϵ� ī�� : % // ���̿� ������� ��� ���� �����͸� �ǹ�

--������� s�� �����ϴ� �����ȸ 

Select * from emp where ename like '%S';

--������� �ι�° ���ڰ� L�� ��� ��ȸ

Select * from emp where ename like '_L';

Select * from emp where ename like '_L%';

 

--������� am���ڰ� ���ԵǾ��ִ� ��� ��ȸ

Select * from emp where ename like '%AM%';

 

--������� AM���ڰ� ���ԵǾ� ���� ���� ���

Select * from emp where ename not like '%AM%';

 

--�־����ִ� ���ڿ����� (_)�� (%)�� ã������ ' '�ȿ� '\_'�̳�, '\%'�� �־ ã�´�.

 

--is null : 0�� �ƴϰ� ������ ����ִ� ���¸� �ǹ�, ������ ��� �Ұ�

select * from emp;

select * from emp where comm=null; --���� �ȵ�

 

--���ϴ� ����� null������ �ҷ� �ö� is null ���

select * from emp where comm is null;

 

--mgr�� null�� ��� ��ȸ

select * from emp where mgr is null;

 

--mgr�� null�� �ƴ� ��� ��ȸ

select * from emp where mgr is not null; --not�� ���� is�� null ���̿� �־���Ѵ�.