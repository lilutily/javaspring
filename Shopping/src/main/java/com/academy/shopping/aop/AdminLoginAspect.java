package com.academy.shopping.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;

import com.academy.shopping.exception.AdminException;

// 관리자모드에서 로그인을 거치지 않고, 진행하는 요청에 대해 거부처리
public class AdminLoginAspect {
	
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws AdminException, Throwable {
		Object returnObj=null; // 원래 호출하려던 메서드의 호출 후 반환되는 객체(String, ModelAndView ...)
		System.out.println("관리자의 컨트롤러 메서드 호출에 관여합니다");
		
		// 세션을 얻어와서 해당 세션에 admin 객체가 들어있는지 판단 및 처리
		Object[] args=joinPoint.getArgs(); // 원래 호출하려던 메서드의 매개변수들
		HttpServletRequest request =null;
		for(int i=0; i<args.length; i++) {
			if(args[i] instanceof HttpServletRequest)  {
				System.out.println("요청 객체 발견 "+args);
				request=(HttpServletRequest)args[i];
			}
		}
		// 매개변수에 요청 객체가 존재한다면?
		HttpSession session=null;
		String uri=request.getRequestURI();
		
		// 로그인이 필요한 서비스와 필요없는 서비스로 조건을 크게 나누자
		if(
				uri.equals("/admin/loginform") ||
				uri.equals("/admin/main") ||
				uri.equals("/admin/registform") ||
				uri.equals("/admin/product/registform") ||
				uri.equals("/admin/product/regist")
				/* 로그인 제외 대상 명단
				uri.equals("/admin/loginform") ||
				uri.equals("/admin/loginform") ||
				uri.equals("/admin/loginform") ||
				*/
				) {
			returnObj=joinPoint.proceed();
		} else {
			if(request!=null) {
				session=request.getSession();
				
				if(session.getAttribute("admin")==null) {
					System.out.println("인증되지 않은 상태");
					throw new AdminException("관리자 로그인이 필요한 서비스입니다"); // 문제... dispatcharServlet로 넘겨버리네..?
				} else {
					// 원래 호출하려 했던 메서드를 대신 호출해주자
					returnObj=joinPoint.proceed();
				}
			}
		}
		
		
		return returnObj;
	}
}
