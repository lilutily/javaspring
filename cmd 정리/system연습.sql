-- hr�̶�� ���̵��� ��й�ȣ�� hr�� �����ϴ� �ҽ�
alter user hr IDENTIFIED by hr;

-- ���̵�� �н����� ����� �ִ� ��ɾ�
create user scott identified by  tiger default tablespace users TEMPORARY TABLESPACE temp;

-- ���� �ο�
grant CONNECT, RESOURCE to scott;


-- ���� �� �۾� ���ο� ���̵� ���� ����� ����
create user lilutily IDENTIFIED by 9902;
-- ���� �ο�
grant connect, resource to lilutily;

-- ���� pc�� �ƴ� ����Ŭ ���� pc ��η� �ؾߵ�
--create tablespace ts�̸� datafile 'C:\\testsql\\test.dbf' size 600m;
create tablespace lilutily datafile 'C:\\testsql\\test.dbf' size 600m;

-- alter user ���̵� default tablespace ts�̸�; 
alter user lilutily default tablespace lilutily;