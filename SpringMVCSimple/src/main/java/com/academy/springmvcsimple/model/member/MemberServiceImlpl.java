package com.academy.springmvcsimple.model.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.springmvcsimple.domain.Emp;
@Service
public class MemberServiceImlpl  implements MemberService{
	// 상위 자료형으로 보유하자
	@Autowired
	private DeptDAO deptDAO;
	
	@Autowired
	private EmpDAO empDAO;
	
	// 한명의 사원 등록이란 부서와 사원 테이블 모두 성공해야 전체를 성공으로 간주하는 트랜잭션 상황이다..
	public int regist(Emp emp) {
		int result=0;
		result=deptDAO.insert(emp.getDept()); // 부서넣기
		// mybatis에 의해 Emp DTO가 참조하고 있었던 Dept DTO는 pk를 포함한 모든 값들이 채워져 있게 된다.
		if(result!=0) { // 부서 등록이 있어야 사원등록도 시도한다..
			result=empDAO.insert(emp); // 사원등록 -> 부서 정보가 다 들어있는 사원정보를 등록하게 된다.
		}
		return result;
	}

	@Override
	public List selectAll() {
		return empDAO.selectAll();
	}

	@Override
	public Emp select(int empno) {
		return empDAO.select(empno);
	}

	@Override
	public int update(Emp emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int empno) {
		// TODO Auto-generated method stub
		return 0;
	}

}
