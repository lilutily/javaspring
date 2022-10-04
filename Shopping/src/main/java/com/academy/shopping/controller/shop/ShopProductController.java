package com.academy.shopping.controller.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.category.TopCategoryService;
import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.product.ProductService;

@Controller
public class ShopProductController {
	@Autowired
	private TopCategoryService topCategoryService;

	@Autowired
	private ProductService productService;

	// 상품 목록 페이지 요청
	@GetMapping("/shop/product")
	public ModelAndView getProductMain(
			HttpServletRequest request,
			@RequestParam(defaultValue = "0") int subcategory_id,
			@RequestParam(defaultValue = "0") int topcategory_id) {

		// 카테고리 가져오기
		List topCategoryList = topCategoryService.selectAll();

		// 하위 카테고리에 소속된 상품가져오기(만일 선택된 하위카테고리가 없는 상태라면 모두 가져오기)
		System.out.println("이 메서드 호출시 subcategory_id 값은 : " + subcategory_id);

		List productList = null;
		
		if(topcategory_id != 0) { // 상위 카테고리를 선택한 경우라면..
			productList=productService.selectByTopId(topcategory_id);
		} else {
			if (subcategory_id == 0) {
				productList = productService.selectAll();
			} else {
				productList = productService.selectBySubyId(subcategory_id);
			}
		}
		

		ModelAndView mav = new ModelAndView("shop/list");
		mav.addObject("topCategoryList", topCategoryList);
		mav.addObject("productList", productList);
		return mav;
	}
	
	// 상품상세요청처리
	@GetMapping("/shop/product/view")
	public ModelAndView getDetail(HttpServletRequest request,int product_id) {
		ModelAndView mav = new ModelAndView("shop/detail");	
		List topCategoryList = topCategoryService.selectAll();
		Product product=productService.select(product_id);
		mav.addObject("topCategoryList", topCategoryList);
		mav.addObject("product",product);
		return mav;
	}
	

}
