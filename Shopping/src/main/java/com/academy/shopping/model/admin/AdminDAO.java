package com.academy.shopping.model.admin;

import java.util.List;

import com.academy.shopping.model.domain.Admin;

public interface AdminDAO {
	public List selectAll();
	public Admin select(int admin_id);
	public Admin selectByIdAndPass(Admin admin); // 로그인 시 필요
	public void insert(Admin admin); // 관리자 등록
	public void update(Admin admin);
	public void delete(Admin admin);
	// int 형 안쓰는 이유! : 혹시 모를 hibernate때문임

}
