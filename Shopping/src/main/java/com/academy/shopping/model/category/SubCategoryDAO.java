package com.academy.shopping.model.category;

import java.util.List;

import com.academy.shopping.model.domain.SubCategory;

public interface SubCategoryDAO {
	public List selectAll();
	public List selectByTopCategoryId(int topcategory_id);
	public SubCategory select(int subcategory_id);
	public void insert(SubCategory subCategory);
	public void update(SubCategory subCategory);
	public void delete(SubCategory subCategory);

}
