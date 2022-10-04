package com.academy.shopping.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	// 로그인 폼 요청처리
	@GetMapping("/admin/loginform")
	public ModelAndView getForm(HttpServletRequest request) {
		return new ModelAndView("admin/login_form");
	}
	// 관리자 등록 요청처리
	@GetMapping("/admin/registform")
	public ModelAndView getRegistForm(HttpServletRequest request) {
		return new ModelAndView("admin/regist");
	}
	
	// 관리자 메인 페이지 요청
	@GetMapping("/admin/main")
	public ModelAndView getMain(HttpServletRequest request) { // AOP을 적용할거면 넣어줘야된다..
		ModelAndView mav = new ModelAndView("admin/index");
		return mav;
	}
}
