package com.academy.shopping.model.order;

import java.util.List;

import com.academy.shopping.model.domain.OrderDetail;

public interface OrderDetailDAO {
	public List selectAll();
	public OrderDetail selectByOrderSummaryId(int ordersummary_id);
	public OrderDetail select(int ordersummary_id);
	public void insert(OrderDetail orderdetail);
	public void update(OrderDetail orderdetail);
	public void delete(OrderDetail orderdetail);
}
