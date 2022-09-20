package com.academy.springmvcsimple.model.member;

import java.util.List;

import com.academy.springmvcsimple.domain.Emp;

/** 이 DAO는 모든 Dept관련된 DAO의 최상위 객체이다
 *   즉 JDBC , Hibernate, Mybatis, JPA 상관없는 최상위 객체
 */
public interface EmpDAO {
	public int insert(Emp emp); 
	public List selectAll();
	public Emp select(int empno); // 한건 가져오기
	public int update(Emp emp);
	public int delete(int empno);
}
