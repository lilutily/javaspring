package com.academy.shopping.model.product;

import java.util.List;

import com.academy.shopping.model.domain.Product;

// DAO는 DB에 넣는것에 집중함
public interface ProductDAO {
	public List selectAll();
	public List selectBySubyId(int subcategory_id);
	public Product select(int product_id);
	public void insert(Product product);
	public void update(Product product);
	public void delete(Product product);
}
