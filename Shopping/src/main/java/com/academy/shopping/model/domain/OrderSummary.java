package com.academy.shopping.model.domain;

import java.util.List;

import lombok.Data;

@Data
public class OrderSummary {
	private int ordersummary_id;
	/*
	 * private int member_id; private int paymethod_id;
	 */
	private int totalbuy;
	private int totalpay;
	private String buydate;
	private Member member; // 누가샀는지 1:1 
	private Paymethod paymethod; // 어떤 결제 방법
	private List<OrderDetail> orderDetailList;
	
}
