package com.academy.springdb.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.academy.springdb.exception.CommentsException;
import com.academy.springdb.model.domain.Comments;
import com.academy.springdb.model.news.CommentsService;

//컨트롤러이다.. 하지만 이 요청은 jsp를 보내는 처리가 아닌, 오직 json,xml 같은 데이터만 보내는
// 비동기 요청에만 반응하는 컨트롤러

@RestController
@ResponseBody // jsp를 응답정보로 보내는 것이 아니라, 스트링 데이터 자체를 응답정보로 보냄
public class NewsRestController {
//	private NewsService newsService;
	@Autowired
	private CommentsService commentsService;
	public NewsRestController() {
		System.out.println("레스트 컨트롤러고 지금 태어났어요");
	}
	@GetMapping("/rest")
	public String test() {
		return "this is data";
	}
	
	// 스프링의 모든 컨트롤러에서는 업무수행시 예외가 발생하면, 해당 예외를 처리할 수 있도록 이벤트를 지원한다
	// 이벤트는 @ExceptionHandler 어노테이션으로 처리한다
	@PostMapping("/comments")
	public String regist(Comments comments) {
		System.out.println(comments);
		commentsService.insert(comments); // 댓글 등록
		return "ok";
	}
	
	// 뉴스기사에 소속된 댓글 목록 요청처리
	@GetMapping("/comments/{news_id}")
	@ResponseBody
	public List getListById(@PathVariable("news_id") int news_id) {
		System.out.println("넘어온 news_id 는 : " + news_id);
		List commentsList=commentsService.selectByNewsId(news_id);
		// 클라이언트가 예측하고 기다리는 데이터는 json, xml 등이다. 하지만 우리가 지금 보유한 객체는
		// 클라이언트가 이해할 수 없는 java객체이다.. 따라서 java객체를 json으로 변환하되, 수동이 아닌 자동으로
		// 진행하려면 외부 라이브러리가 필요하다 (jsp시간에 Gson을 이용해봄)
		// 스프링도 외부 라이브러리를 이용한다.
		
		return commentsList;
	}
	@ExceptionHandler(CommentsException.class)
	public String handleException(CommentsException e) { // 예외가 발생하면 해당 예외를 객체의 인스턴스를 생성하여 우리가 정의해 놓은 메서드의 매개변수로 전달해준다.
		return e.getMessage();
	}
}
