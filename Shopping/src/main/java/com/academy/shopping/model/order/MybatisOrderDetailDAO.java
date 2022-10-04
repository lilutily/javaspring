package com.academy.shopping.model.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.shopping.exception.OrderDetailException;
import com.academy.shopping.model.domain.OrderDetail;
@Repository
public class MybatisOrderDetailDAO implements OrderDetailDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetail selectByOrderSummaryId(int ordersummary_id) {
		return sqlSessionTemplate.selectOne("OrderDetail.selectByOrderSummaryId", ordersummary_id);
	}

	@Override
	public OrderDetail select(int ordersummary_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(OrderDetail orderdetail) throws OrderDetailException {
		// TODO Auto-generated method stub
		int result=sqlSessionTemplate.insert("OrderDetail.insert", orderdetail);
		if(result==0) {
			throw new OrderDetailException("주문상세 정보가 등록되지 않았습니다");
		}
	}

	@Override
	public void update(OrderDetail orderdetail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(OrderDetail orderdetail) {
		// TODO Auto-generated method stub
		
	}

}
