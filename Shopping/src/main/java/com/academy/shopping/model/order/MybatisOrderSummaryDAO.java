package com.academy.shopping.model.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.shopping.exception.OrderSummaryException;
import com.academy.shopping.model.domain.Member;
import com.academy.shopping.model.domain.OrderDetail;
import com.academy.shopping.model.domain.OrderSummary;

@Repository
public class MybatisOrderSummaryDAO implements OrderSummaryDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("OrderSummary.selectAll");
	}

	@Override
	public OrderSummary selectByCustomerId(Member member) {
		return null;
	}
	
	@Override
	public OrderSummary select(int ordersummary_id) {
		return sqlSessionTemplate.selectOne("OrderSummary.select", ordersummary_id);
	}

	@Override
	public void insert(OrderSummary orderSummary) throws OrderSummaryException {
		// TODO Auto-generated method stub
		int result= sqlSessionTemplate.insert("OrderSummary.insert",orderSummary);
		if(result==0) {
			throw new OrderSummaryException("주문정보가 입력되지 않았습니다");
		}
		
	}

	@Override
	public void update(OrderSummary orderSummary) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(OrderSummary orderSummary) {
		// TODO Auto-generated method stub
		
	}

	

}
