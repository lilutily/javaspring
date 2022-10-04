package com.academy.shopping.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.http.ResponseEntity;

import com.academy.shopping.exception.MemberException;

public class RestMemberLoginAspect {
	String TAG=this.getClass().getName();// 현재 클래스 명이 담아짐
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable, MemberException {
		Object returnObj=null;
		HttpServletRequest request=null;
		HttpSession session=null;
		ResponseEntity entity=null;
		System.out.println(TAG+"Rest 방식 회원 로그인 판단에 관여");
		System.out.println(TAG+"호출하려던 메서드"+joinPoint.getSignature().getName());
		
		Object[] args= joinPoint.getArgs();
		for(Object arg : args) {
			System.out.println(TAG+"매개변수"+arg);
			if(arg instanceof HttpServletRequest) {
				request=(HttpServletRequest) arg;
			}
		}
		
		// 제외될 명단을 작성하기 위한 uri조사
		String uri =request.getRequestURI();
		
		if(
				uri.equals("/rest/member/login") ||
				uri.equals("/rest/member/check") || // 비동기 ID 중복 체크
				uri.equals("/rest/member") // 비동기 회원가입
				) {
			returnObj=joinPoint.proceed();
			if(returnObj instanceof ResponseEntity) {
				entity=(ResponseEntity)returnObj;
			}		
		} else {
			//회원 로그인 여부 판단
			session=request.getSession();
			if(session.getAttribute("member")==null) {
				throw new MemberException("회원 로그인이 필요한 서비스입니다(rest)");
			} else {
				returnObj=joinPoint.proceed(); // 원래 호출하려면 메서드 호출
				if(returnObj instanceof ResponseEntity) {
					entity=(ResponseEntity)returnObj;
					System.out.println("엔터티 반환함"+entity);
				}
			}
		}
		
		
		
		return returnObj;
	}
}
