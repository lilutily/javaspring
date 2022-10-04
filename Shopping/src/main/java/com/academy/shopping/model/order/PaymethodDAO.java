package com.academy.shopping.model.order;

import java.util.List;

import com.academy.shopping.model.domain.Paymethod;

public interface PaymethodDAO {
	public List selectAll();
	public Paymethod select(int ordersummary_id);
	public void insert(Paymethod paymethod);
	public void update(Paymethod paymethod);
	public void delete(Paymethod paymethod);
}
