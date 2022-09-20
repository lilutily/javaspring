package com.academy.springmvcbasic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.academy.springmvcbasic.model.repository.NoticeDAO;

// 공지게시판의 목록요청을 처리하는 하위 컨트롤러 (3,4 단계 수헹)
public class NoticeListController implements Controller{
	private NoticeDAO noticeDAO; // 상위객체로 해야되는데 일단은 진행
	
	// setter injectionj주입
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 3단계 : 일 시킨다
		List boardList=noticeDAO.selectAll();
		
		// 4단계 : view로 전달할 것이 있으면 결과 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList); // 요청객체에 저장하는 효과를 냄
		mav.setViewName("board/list");
		return mav;
	}
	
}
