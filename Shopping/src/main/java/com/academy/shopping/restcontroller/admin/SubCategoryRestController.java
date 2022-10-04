package com.academy.shopping.restcontroller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.shopping.model.category.SubCategoryService;
import com.academy.shopping.model.domain.SubCategory;

@RestController
public class SubCategoryRestController {
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	// 관리자 - 해당 상위 카테고리에 소속된 하위 카테고리 목록 가져오기 요청처리
	@GetMapping("/admin/subcategory/{topcategory_id}") // 비록 url이지만 {}에 들어가는것은 경로가 아닌것을 요청해보자
	public List getSubList(@PathVariable("topcategory_id") int topcategory_id) { 
		System.out.println("넘어온 topcategory_id : " + topcategory_id);
		List subList=subCategoryService.selectByTopCategoryId(topcategory_id);
		return subList;
	}
	
	@PostMapping("/admin/subcategory")
	public ResponseEntity registSub(SubCategory subCategory) {
		subCategoryService.insert(subCategory);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html;charset=utf-8");
		ResponseEntity entity = new ResponseEntity(HttpStatus.OK);
		return entity;
	}
}
