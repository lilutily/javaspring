package com.academy.shopping.model.category;

import java.io.File;
import java.util.List;

import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.domain.SubCategory;

public interface SubCategoryService {
	public List selectAll();
	public List selectByTopCategoryId(int topcategory_id);
	public SubCategory select(int subcategory_id);
	public void regist(Product product, String savePath); // DAO + FILE --> 서비스와 DAO의 차이가 생기는 시점
	public void registByExcel(File file, String ori, String dest); // 액셀파일을 매개변수로 넘긴다
	public void insert(SubCategory subCategory);
	public void update(SubCategory subCategory);
	public void delete(SubCategory subCategory);

}
