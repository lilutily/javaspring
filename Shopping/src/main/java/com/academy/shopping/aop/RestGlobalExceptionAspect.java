package com.academy.shopping.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.academy.shopping.exception.FileException;
import com.academy.shopping.exception.MemberException;
import com.academy.shopping.exception.ProductException;
import com.academy.shopping.model.util.Message;

//RestController에서 발생되는 모든 예외를 여기서 잡는다

@ControllerAdvice
public class RestGlobalExceptionAspect {
	@ExceptionHandler(MemberException.class)
	public ResponseEntity<Message> handleException(MemberException e) {
		Message message = new Message(0, e.getMessage());
		ResponseEntity<Message> entity= new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<Message> handleException(ProductException e) {
		Message message = new Message(0, e.getMessage());
		ResponseEntity<Message> entity= new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}
	@ExceptionHandler(FileException.class)
	public ResponseEntity<Message> handleException(FileException e) {
		Message message = new Message(0, e.getMessage());
		ResponseEntity<Message> entity= new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}
}
