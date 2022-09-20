package com.academy.springdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.springdb.exception.NewsException;
import com.academy.springdb.model.domain.News;
import com.academy.springdb.model.news.NewsService;

@Controller /** 스프링 컨테이너가 메모리에 올릴 대상이 될 수 있도록.. */
public class NewsController {
	public NewsController() {
		System.out.println("컨트롤러고 지금 스프링에 의해 태어남");
	}
	@Autowired
	private NewsService newsService;
	
	@GetMapping("/news/list")
	public ModelAndView selectAll() {
		List newsList=newsService.selectAll();
		ModelAndView mav= new ModelAndView("news/list");
		mav.addObject("newsList", newsList);
		return mav;
	}
	// 글쓰기 요청
	@GetMapping("/news/registform")
	public ModelAndView registform() {
		
		ModelAndView mav= new ModelAndView("news/regist");
		return mav;
	}
	
	// 글쓰기 요청 처리
	@PostMapping("/news/regist")
	public String regist(News news) {
		newsService.regist(news);
		return "redirect:/news/list";
	}
	
	@GetMapping("/news/content")
	public ModelAndView select(int news_id) {
		News news=newsService.select(news_id);
		ModelAndView mav= new ModelAndView("news/content");
		mav.addObject("news", news);
		return mav;
	}
	
	// 수정요청 처리
	@PostMapping("/news/update")
	public ModelAndView update(News news) {
		newsService.update(news); // 익셉션
		ModelAndView mav= new ModelAndView();
		mav.setViewName("redirect:/news/content?news_id="+news.getNews_id()); // 상세보기로 재접속유도
		return mav;
	}
	
	// 삭제요청 처리
	@GetMapping("/news/delete")
	public String delete(int news_id) {
		
		// Transaction : 세부업무가 모두 성공해야 전체를 성공으로 간주하는 업무(DML) 수행단위 
		
		newsService.delete(news_id);
		
		
		
		return "redirect:/news/list";
	}
	
	// 스프링 MVC 컨트롤러의 메서드들 중에서 예외가 발생할때, 이 예외를 처리할 메서드를 지원해준다
	@ExceptionHandler(NewsException.class)
	public ModelAndView handleException(NewsException e) {
		
		// 클라이언트가 에러메시지를 볼수있도록 뷰로 저장한다
		ModelAndView mav= new ModelAndView("error/result");
		mav.addObject("msg",e.getMessage());
		return mav;
	}
	
	
}
