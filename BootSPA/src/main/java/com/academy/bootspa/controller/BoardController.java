package com.academy.bootspa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.bootspa.model.domain.Board;

@Controller
public class BoardController {
	@GetMapping("/board/main")
	public ModelAndView getMain() {
		System.out.println("메인요청받았습니다");
		return new ModelAndView("main");
	}
	
	// 등록요청 처리
	@PostMapping("/board/regist")
	public ModelAndView regist(Board board) {
		System.out.println("동기방식의 등록요청 받음"+board);
		return null;
	}
}
