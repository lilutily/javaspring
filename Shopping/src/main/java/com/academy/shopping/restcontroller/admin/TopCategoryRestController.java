package com.academy.shopping.restcontroller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.shopping.exception.TopCategoryException;
import com.academy.shopping.model.category.TopCategoryService;
import com.academy.shopping.model.domain.TopCategory;

@RestController
public class TopCategoryRestController {
	@Autowired
	private TopCategoryService topCategoryService;
	// 관리자 - 카테고리 등록 요청 처리
	@PostMapping("/admin/topcategory")
	public ResponseEntity regist(TopCategory topCategory) {
		topCategoryService.insert(topCategory);
		
		ResponseEntity entity= new ResponseEntity(HttpStatus.CREATED); // 200
		return entity;
	}
	
	// 관리자 - 상위 카테고리 목록 요청 처리
	@GetMapping("/admin/topcategory")
	public List getList() {
		List topList=topCategoryService.selectAll();
		return topList; // List -> json 배열로 변환(bean으로 동록한 MessageConverter가 해준다)
	}
	@ExceptionHandler(TopCategoryException.class)
	public ResponseEntity handleException(TopCategoryException e) {
		ResponseEntity entity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
}
