package com.academy.shopping.restcontroller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.academy.shopping.exception.AdminException;
import com.academy.shopping.model.admin.AdminService;
import com.academy.shopping.model.domain.Admin;
import com.academy.shopping.model.util.HashManager;

// Contoller  vs   RestContoller 자동으로 @ResponseBody가 붙여짐..


@RestController
public class AdminRestContoller {
	@Autowired
	private AdminService adminService;
	@Autowired
	private HashManager hashManager;
	@PostMapping("/admin")
	@ResponseBody // RestController에서는 이 속성이 자동부여됨  그리고 속성의 의미는?
	// 결과 뷰 페이지를 매핑하는것이 아니라 순수 데이터를 전송하는 효과 따라서 비동기 응답에 적절하다
	public ResponseEntity regist(Admin admin) {
		adminService.insert(admin);
		// ResponseEntity 응답정보를 전담하는 객체 (필수는 아니지만 http응답정보에 최적화되있어서 편함)
		return new ResponseEntity(HttpStatus.OK); // WEB-INF/views/~~.jsp
	}
	
	// 로그인 요청을 처리한다
	@PostMapping("admin/login")
	public ResponseEntity<String> login(Admin admin, HttpServletRequest request) {
		// db 패스워드를 비교하기 전에 ,먼저 클라이언트가 전송한 패스워드를 hash값으로 변경한 후 비교해야함
		String hashedValue=hashManager.getConvertedPassword(admin.getPass()); // 평문 -> hash
		admin.setPass(hashedValue); // DTO의 패스워드 값을 hash값으로 교체
		Admin obj=adminService.selectByIdAndPass(admin); // 해당 아이디와 패스워드가 일치하는 회원이 있을때, DTO가 null이 아니다
		System.out.println("로그인 결과 : " + obj);
		
		// 세션에 정보를 담아두자
		HttpSession session=request.getSession();
		session.setAttribute("admin", obj);
		
		ResponseEntity<String> entity=new ResponseEntity<String>("1", HttpStatus.OK);
		return entity;
	}
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity handleException(AdminException e) {
		ResponseEntity<String> entity=new ResponseEntity<String>("0", HttpStatus.OK);
		return entity;
	}
	
}
