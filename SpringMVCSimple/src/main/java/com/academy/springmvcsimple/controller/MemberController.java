package com.academy.springmvcsimple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.academy.springmvcsimple.domain.Emp;
import com.academy.springmvcsimple.model.member.MemberService;

@Controller
public class MemberController {
	String TAG=this.getClass().getName(); // membercontroller 반환
	
	// 
	@Autowired
	private MemberService memberService; // DI를 구현하기 위해 상위 인터페이스를 보유한다.
	
	// 사원등록
	@RequestMapping(value="/member/regist", method=RequestMethod.POST)
	public ModelAndView regist(Emp emp) {
		System.out.println(TAG +": 사원명 "+emp.getEname());
		System.out.println(TAG +": 희망급여 "+emp.getSal());
		System.out.println(TAG +": 희망부서 "+emp.getDept().getDname());
		
		memberService.regist(emp); // 부서+사원 등록 업무가 추상화 되어 표현됨 .. 즉 부장님한테 맡김
		
		return new ModelAndView("redirect:/member/list");
//		return new ModelAndView("/member/list");
	}
	// 사원목록 요청처리
	@RequestMapping(value="/member/list", method=RequestMethod.GET)
	public ModelAndView selectAll() {
		
		List memberList=memberService.selectAll(); // 3단계 : 일시키기
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberList", memberList); // 4단계 저장하기
		mav.setViewName("member/list");
		return mav;
	}
	
	// 사원정보 한건 가져오기 요청처리
	@RequestMapping(value="/member/detail", method=RequestMethod.GET)
	public ModelAndView select(int empno) {
		Emp emp=memberService.select(empno);
		ModelAndView mav = new ModelAndView("member/detail");
		mav.addObject("emp",emp);
		return mav;
	}
}
