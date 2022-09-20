package com.academy.springmvcsimple.model.member;

import java.util.List;

import com.academy.springmvcsimple.domain.Emp;

// 서비스는 Model (데이터 또는 로직)이다
// 사원과 관련된 업무를 처리하는 서비스 객체
// 만약 서비스가 없다면, 서비스가 부담해야 할 업무를 컨르롤러가 해버리게 된다..

public interface MemberService {
	
	/*
	 * empDAO.insert(emp); // 자식의 메서드를 호출하게 된다 -> 다형성 deptDAO.insert(emp.getDept());
	 */
	// 트랜잭션 상황이라 따로 만드는게 아니라 하나로 묶어야한다
	public int regist(Emp emp); // 메서드는 하나이지만, 내부적으로는 사원등록이란(부서+사원 둘다 성공해야 전체를 성공으로 간주하는 트랜잭션 상황)
	public List selectAll(); // 사원목록 가져오기
	public Emp select(int empno); // 사원하명 정보가져오기
	public int update(Emp emp);
	public int delete(int empno);
}
