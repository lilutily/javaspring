package com.academy.shopping.model.admin;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.shopping.exception.AdminException;
import com.academy.shopping.model.domain.Admin;

@Repository
public class MybatisAdminDAO implements AdminDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin select(int admin_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin selectByIdAndPass(Admin admin) throws AdminException {
		Admin obj=sqlSessionTemplate.selectOne("Admin.selectByIdAndPass", admin);
		if(obj== null) {
			throw new AdminException("회원정보 불일치");
		}
		return obj;
	}

	@Override
	public void insert(Admin admin) throws AdminException {
		int result=sqlSessionTemplate.insert("Admin.insert", admin); // 등록
		if(result==0) {
			throw new AdminException("관리자 등록 실패");
		}
		
	}

	@Override
	public void update(Admin admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Admin admin) {
		// TODO Auto-generated method stub
		
	}

}
