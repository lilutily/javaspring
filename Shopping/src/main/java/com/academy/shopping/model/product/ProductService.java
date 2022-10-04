package com.academy.shopping.model.product;

import java.io.File;
import java.util.List;

import com.academy.shopping.model.domain.Product;


public interface ProductService {
	public List selectAll();
	public List selectByTopId(int topcategory_id);
	public List selectBySubyId(int product_id);
	public Product select(int product_id);
	public void regist(Product product, String savePath); // DAO + FILE --> 서비스와 DAO의 차이가 생기는 시점
	public void registByExcel(File file, String ori, String dest); // 액셀파일을 매개변수로 넘긴다
	public void update(Product product);
	public void remove(Product product, String dest); // 파일삭제+db삭제
}
