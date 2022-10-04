package com.academy.shopping.restcontroller.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.shopping.exception.MemberException;
import com.academy.shopping.model.domain.Member;
import com.academy.shopping.model.member.MemberService;
import com.academy.shopping.model.util.Message;



// 비동기로 드가자아..
@RestController
public class ShopMemberRegistController {
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/member")
	public ResponseEntity<Message> regist(HttpServletRequest request, Member member) {
		System.out.println(member.getCustomer_id());
		System.out.println(member.getCustomer_name());
		System.out.println(member.getCustomer_pass());
		System.out.println(member.getCustomer_email());
		
		memberService.insert(member);
		// HttpStatus 100 작업진행중, 200 성공실패, 300~, 400(자원이없음), 500(서버멈춤 심각함)
		Message message = new Message(1,"가입성공");
		ResponseEntity<Message> entity= new ResponseEntity<Message>(message, HttpStatus.OK); // create로 상세화 하거나 ok로 퉁치기
		return entity;
	}
	@GetMapping("/member/check")
	public ResponseEntity<Message> getId(HttpServletRequest request, @RequestParam("customer_id") String customer_id) {
		System.out.println("검증할 아이디 : " + customer_id);
		memberService.selectCustomerId(customer_id);
		Message message = new Message(1,customer_id+"는 사용가능한 아이디 입니다");
		ResponseEntity<Message> entity= new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}
	
	// 로그인  요청 처리
	@PostMapping("/member/login")
	public ResponseEntity<Message> login(HttpServletRequest request, Member member) {
		Member result=memberService.selectByIdAndPass(member);
		// 로그인 성공하면 회원정보를 유지할 수 있도록 세션에 MemberDTO를 담아주자..
		HttpSession session=request.getSession();
		session.setAttribute("member", result);
		Message message = new Message(1, "로그인 성공");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}
	
	
	
	
}
