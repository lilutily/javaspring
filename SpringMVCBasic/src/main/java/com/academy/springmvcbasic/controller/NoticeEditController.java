package com.academy.springmvcbasic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.academy.springmvcbasic.domain.Notice;
import com.academy.springmvcbasic.model.repository.NoticeDAO;

// 수정요청을 처리하는 하위컨트롤러
public class NoticeEditController implements Controller{
	// new하게 되면 NoticeEditController와 NoticeDAO간 결합도가 너무 강해진다... 
	// 결합도가 너무 강해지면, 만일 NoticeDAO의 클래스명이나 메서드명이 바뀌면? DI를 이용하여 약화시킬 수 있다
	private NoticeDAO noticeDAO;
	
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 3단계 수정하기
		String notice_id= request.getParameter("notice_id");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setNotice_id(Integer.parseInt(notice_id));
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		noticeDAO.update(notice); // 일시키기
		
		ModelAndView mav = new ModelAndView("redirect:/board/content?notice_id="+notice_id);
		return mav;
	}

}
