package com.academy.shopping.restcontroller.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.shopping.model.util.Message;
import com.academy.shopping.restcontroller.admin.Cart;

@RestController
public class PaymentRestController {

	@PostMapping("/cart")
	public ResponseEntity<Message> registCart(HttpServletRequest request, Cart cart) {
		cart.setQuantity(1); // 처음 장바구니에 담을때 디폴트 값을 1로 설정
		System.out.println(cart.getProduct_name());
		
		// 고객이 넘긴 상품 1개를 DB에 넣지않고 메모리에 저장하자
		// 메모리에 넣는 이유 -> session 공부용 목적
		// session 장점: table 컬럼에 존재하지않는 속성도 담을수 있다 -> 자유로움
		HttpSession session = request.getSession();
		session.setAttribute(Integer.toString(cart.getProduct_id()), cart); // 세션은 Map 자료형이므로, 순서가 없음. 따라서 넣을때 유일한 구분값인 key값을 부여한다
		
		// 응답 메세지 생성
		Message message = new Message(1, "장바구니에 상품이 담겼습니다");
		ResponseEntity<Message> entity= new ResponseEntity<Message>(message, HttpStatus.OK);
		
		return entity;
	}
	
}
