package com.academy.shopping.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.exception.MemberException;
import com.academy.shopping.model.category.TopCategoryService;

// 횡단적 관심사항에 대한 공통코드를 작성해놓은 객체(하나의 관점으로 둘 예정)
// jsp에 최적화됨
public class MemberLoginAspect {
	@Autowired
	private TopCategoryService topCategoryService;
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable, MemberException {
		Object returnObj=null;
		HttpServletRequest request=null;
		HttpSession session=null;
		System.out.println("회원 로그인 판단에 관여");
		
		// 세션 끄집어 내기
		Object[] args=joinPoint.getArgs();
		
		for(Object arg : args) {  // 요청 객체 잡아내기
			if(arg instanceof HttpServletRequest) {
				request=(HttpServletRequest)arg;
			}
		}
		
		String uri=request.getRequestURI();
		ModelAndView mav= null;
		if(
				// 로그인하지 않고도 접근 가능한 uri 명단
				uri.equals("/shop") ||
				uri.equals("/shop/member/registform") ||
				uri.equals("/shop/member/loginform") ||
				uri.equals("/shop/member/login") ||
				uri.equals("/shop/member/logout") ||
				uri.equals("/shop/product") ||
				uri.equals("/shop/product/view") 
				
		  ) {
			// 원래 호출하려던 메서드 호출
			returnObj=joinPoint.proceed();
			if(returnObj instanceof ModelAndView) {
				mav=(ModelAndView)returnObj;
			}
		} else { // 세션체크
			session=request.getSession();
			if(session.getAttribute("member")==null) {
				throw new MemberException("회원 로그인이 필요한 서비스입니다");
			} else {
				returnObj=joinPoint.proceed();
				if(returnObj instanceof ModelAndView) {
					mav=(ModelAndView)returnObj;
				}
			}
		}
		if(mav !=null) { // 동생 컨트롤러의 메서드가 ModelAndView를 반환한 경우만..
			List topCategoryList=topCategoryService.selectAll();
			mav.addObject("topCategoryList",topCategoryList);
			returnObj= mav;
		}
		return returnObj;
	}
}
