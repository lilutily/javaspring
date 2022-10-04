package com.academy.shopping.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.domain.OrderSummary;
import com.academy.shopping.model.order.OrderSummaryService;
import com.academy.shopping.model.product.ProductService;

@Controller
@RequestMapping("/admin") // 공통 uri는 밖으로 빼놓을 수 있음
public class OrderController {
	
	@Autowired
	private OrderSummaryService orderSummaryService;
	
	@Autowired
	private ProductService productService;
	

	
	String TAG=this.getClass().getName();
	
	// 주문 목록 요청 처리 -> 링크로 날라오니까 get방식
	@GetMapping("/order/list")
	public ModelAndView getList(HttpServletRequest request) {
		List orderSummaryList=orderSummaryService.selectAll();
		System.out.println(TAG+"주문요약 리스트 :" +orderSummaryList);
		ModelAndView mav = new ModelAndView("/admin/order/order_list");
		mav.addObject("orderSummaryList", orderSummaryList);
		return mav;
	}
	
	// 주문 상세 정보 요청 처리
	@GetMapping("/order/detail")
	public ModelAndView getDetail(HttpServletRequest request, int ordersummary_id) {
		// 주문상세 목록 가져오기
		OrderSummary orderSummary=orderSummaryService.select(ordersummary_id);
		ModelAndView mav = new ModelAndView("/admin/order/order_detail");
		mav.addObject("orderSummary", orderSummary);
		return mav;
	}
	
	
}
