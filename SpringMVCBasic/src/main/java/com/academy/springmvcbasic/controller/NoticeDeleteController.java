package com.academy.springmvcbasic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.academy.springmvcbasic.model.repository.NoticeDAO;

// 삭제를 처리하는 하위 컨트롤러
public class NoticeDeleteController implements Controller{
	private NoticeDAO noticeDAO;
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// delete from notice where notice_id=? pk
		int notice_id= Integer.parseInt(request.getParameter("notice_id")); 
		noticeDAO.delete(notice_id); // 일시키기
		return new ModelAndView("redirect:/board/list");
	}

}