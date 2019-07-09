package com.immoc.sell.service;

import java.util.List;

import com.immoc.sell.dataobject.ProductCategory;

public interface CategoryService {
	
	ProductCategory findOne(Integer categoryId); // 卖家端使用
	
	List<ProductCategory> findAll(); // 卖家端使用
	
	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList); // 买家端使用
	
	ProductCategory save(ProductCategory productCategory); // 买家端使用
}
