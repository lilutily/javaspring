package com.academy.springmvcbasic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.academy.springmvcbasic.domain.Notice;
import com.academy.springmvcbasic.model.repository.NoticeDAO;

// 글쓰기 요청을 처리하는 하위 컨트롤러
public class NoticeRegistController implements Controller{
	private NoticeDAO noticeDAO;
	
	// 생성자 메서드 주입
	public NoticeRegistController(NoticeDAO noticeDAO) {
		this.noticeDAO=noticeDAO;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("글쓰기 요청받음");
		// 3단계 일시키기
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		// 이렇게 되면 한글이 다 깨지니까 입구에서 먼저넣어두자 -> web.xml로 

		Notice notice= new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		noticeDAO.insert(notice);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/list"); // 지정한 뷰 이름으로 재접속! (포워딩 아님!)
		return mav;
	}

}
