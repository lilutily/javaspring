package com.academy.shopping.model.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.shopping.model.domain.Paymethod;

@Repository
public class MybatisPaymethodDAO implements PaymethodDAO{
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("Paymethod.selectAll");
	}

	@Override
	public Paymethod select(int ordersummary_id) {
		return sqlSessionTemplate.selectOne("Paymethod.select", ordersummary_id);
	}

	@Override
	public void insert(Paymethod paymethod) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Paymethod paymethod) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Paymethod paymethod) {
		// TODO Auto-generated method stub
		
	}

}
