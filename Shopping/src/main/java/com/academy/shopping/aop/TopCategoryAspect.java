package com.academy.shopping.aop;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.category.TopCategoryService;

/**
 * 쇼핑몰에서 상위카테고리는 어디서든 보여줄 정보이므로 어플리케이션의 횡단적 관심사항에 해당된다 따라서 상위 카테고리목록을 가져오는 코드를
 * 별도의 객체로 정의하여, AOP의 Aspect로 정의해놓고, 필요시 이 코드를 관여시킨다.
 */
public class TopCategoryAspect {
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	// 이 메서드는 , 쇼핑몰의 상위 카테고리를 필요로 하는 모든 메서드에서 공통적으로 동작할 예정임
	public Object getCategoryList(ProceedingJoinPoint joinPoint) {
		// System.out.println("컨트롤러가 동작할때 A.S.P.E.C.T. 관여하는중");

		// 원래 호출하려던 객체명 알아맞추기
		Object target = joinPoint.getTarget();
		System.out.println("호출하려던 객체는 " + target.getClass().getName());
		
		System.out.println("호출하려던 메서드는 " +joinPoint.getSignature()); 
		
		Object returnObj = null;
		try {
			returnObj = joinPoint.proceed(); // 원래 호출하려던 메서드 호출하게 해줌(가던길을 대신 가게한다)
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		// 원래의 요청대신 Aspect(프록시=대리)가 컨트롤러의 메서드를 대신 호출하고, 반환된 ModelAndView에
		// 정보를 심어본다
		if(returnObj instanceof ModelAndView) { // 자료형이 ModelAndView 라면...
			ModelAndView mav = (ModelAndView) returnObj; // 형변환! 그러면 topcategoryService수행을 해줘야되니까 가져와야겠음
			List topCategoryList=topCategoryService.selectAll();
			mav.addObject("topCategoryList",topCategoryList);
			returnObj=mav; // mav만 나올수가 없어서  ModelAndVIew인 경우만 mav로 변환
		}
		return returnObj; // dispatcherServlet에게 반환되며, 이때 형님(dispatcherServlet)은 viewResolver에게 jsp페이지를 얻기위한 해석을 맡긴다

	}
}
