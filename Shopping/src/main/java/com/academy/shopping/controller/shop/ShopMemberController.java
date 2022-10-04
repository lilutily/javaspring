package com.academy.shopping.controller.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.category.TopCategoryService;

@Controller
public class ShopMemberController {
	@Autowired
	private TopCategoryService topCategoryService;
	@GetMapping("/shop/member/registform")
	public ModelAndView getRegistForm(HttpServletRequest request) {
		
		List topCategoryList=topCategoryService.selectAll();
		ModelAndView mav = new ModelAndView("shop/member/join");
		mav.addObject("topCategoryList", topCategoryList);
		return mav;
	}
	
	@GetMapping("/shop/member/loginform")
	public ModelAndView getLoginForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("shop/member/login");
		List topCategoryList=topCategoryService.selectAll();
		mav.addObject("topCategoryList", topCategoryList);
		return mav;
	}
	
	@GetMapping("/shop/member/logout")
	public ModelAndView logout(HttpServletRequest request) {
		// 세션을 무효화 시킴, 이 시점부터는 세션에 담았던 모든 정보를 참조할 수 없게된다
		HttpSession session= request.getSession();
		session.invalidate();
		ModelAndView mav =new ModelAndView("redirect:/shop"); // 페이지 이동 , 새로고침이 발생해야되므로  redirect
		return mav;
	}
}
