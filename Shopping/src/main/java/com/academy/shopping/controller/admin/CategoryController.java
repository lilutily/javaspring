package com.academy.shopping.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {
	
	// 관리자 - 카테고리 관리 메인 요청
	@GetMapping("/admin/category/list")
	public ModelAndView categoryMain(HttpServletRequest request) {
		HttpSession session=request.getSession();
		ModelAndView mav=null;
		if(session.getAttribute("admin")==null) {
			mav = new ModelAndView("admin/error/auth");
		} else {
			mav= new ModelAndView("admin/category/main");
		}
		return mav;
	}
}
