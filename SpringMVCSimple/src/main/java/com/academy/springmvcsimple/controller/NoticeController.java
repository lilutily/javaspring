package com.academy.springmvcsimple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.academy.springmvcsimple.domain.Notice;
import com.academy.springmvcsimple.model.notice.NoticeDAO;

// 이전과는 달리 , 하나의 게시판에 사용되는 컨트롤러를 매기능마다 1:1대응하게 클래스를 만들지 않고 
// 게시판 하나당 하나의 컨트롤러를 만들자
// 스프링의 버전이 올라갈수록 컨트롤러 클래스는 자유도가 높아졌기 때문에, 특정 객체를 상속받거나, 구현해야할 의무가 사라졌다

 /** 이 어노테이션을 선언하는 순간부터 스프링 MVC 의 각종 기능을 사용할 수 있다.
특히 scan의 대상이 될 수 있다. 따라서 xml에 이 컨트롤러를 명시하지 않아도 인스턴스가 올라갈 수 있다.*/
@Controller
public class NoticeController {
	@Autowired
	private NoticeDAO noticeDAO;
	// 목록 요청처리 메서드
	@RequestMapping(value="/board/list", method=RequestMethod.GET)
	public String selectAll(Model model) { //  String은 View역할임
		System.out.println("noticeDAO의 주소값은 " + noticeDAO);
		List boardList=noticeDAO.selectAll(); // 3단계
		model.addAttribute("boardList", boardList);
		return "board/list";
	}
	// 글 내용보기 요청처리 메서드
	@RequestMapping(value="/board/content", method=RequestMethod.GET)
	public ModelAndView select(int notice_id) { 
		// spring 3.x 부터는 피라미터를 받기 위해 request 객체를 굳이 명시할 필요 없이, 파라미터명과 메서드의 매개변수명이 일치할 경우 자동 매핑이 이루어진다..
		System.out.println("notice_id : " + notice_id);
		
		// 3단계 : 일시키기
		Notice notice=noticeDAO.select(notice_id);
		
		// 4단계 : 결과 저장
		ModelAndView mav= new ModelAndView();
		mav.addObject("notice",notice);
		mav.setViewName("board/content");
		return mav;
	}
	// 삭제처리 메서드
	/**
	@RequestMapping(value="/board/delete", method=RequestMethod.GET)
	public ModelAndView delete(int notice_id) {
		int notice=noticeDAO.delete(notice_id);
		return new ModelAndView("redirect:/board/list");
	}
	*/
	@RequestMapping(value="/board/delete", method=RequestMethod.GET)
	public String delete(int notice_id) {
		noticeDAO.delete(notice_id);
		return "redirect:/board/list";
	}
	
	// 글쓰기 요청처리 메서드
	@RequestMapping(value="/board/regist",method=RequestMethod.POST)
	public String insert(Notice notice) {
		int result= noticeDAO.insert(notice);
		return "redirect:/board/list";
	}
	// 수정 요청처리 메서드
	@RequestMapping(value="/board/edit", method=RequestMethod.POST)
	public String edit(Notice notice) {
		int result=noticeDAO.update(notice);
		return "redirect:/board/content?notice_id="+notice.getNotice_id();
	}
	/** 
	 @RequestMapping(value="/board/edit", method=RequestMethod.POST)
	 public ModelAndView edit() {
	 	noticeDAO.update(notice);
	 	return new ModelAndView("redirect:/board/content?notice_id="+notice.getNotice_id());
	 }
	  */
}
