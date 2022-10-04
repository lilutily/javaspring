package com.academy.shopping.model.member;

import java.util.List;

import com.academy.shopping.model.domain.Member;

public interface MemberDAO {
	public List selectAll();
	public Member select(int member_id);
	public Member selectByIdAndPass(Member member);
	public Member selectCustomerId(String customer_id);
	public void insert(Member member);
	public void delete(Member member);
	public void update(Member member);
}
