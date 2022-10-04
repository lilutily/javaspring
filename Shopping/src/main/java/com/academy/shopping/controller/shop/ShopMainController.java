package com.academy.shopping.controller.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.category.TopCategoryService;

// 쇼핑몰 메인에 대한 요청처리
@Controller
public class ShopMainController {
	@Autowired
	private TopCategoryService topCategoryService;
	
	@GetMapping("/shop")
	public ModelAndView getMain(HttpServletRequest request) {
		// 카테고리 가져오기
		// 신상품 및 각종 기획상품 진열
		ModelAndView mav= new ModelAndView("shop/index");
		
		/* 아래의 코드는 앞으로 AOP를 이용하여 Aspect에게 맡긴다
		 * List topCategoryList=topCategoryService.selectAll(); // 3단계 : 일 시키기
		 * mav.addObject("topCategoryList", topCategoryList); // 4단계 : 저장
		 */		
		return mav;
		
	}
}
