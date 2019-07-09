package com.immoc.sell.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immoc.sell.dataobject.ProductCategory;
import com.immoc.sell.repository.ProductCategoryRepository;
import com.immoc.sell.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private ProductCategoryRepository repository;
	
	@Override
	public ProductCategory findOne(Integer categoryId) {
		// TODO Auto-generated method stub
		return repository.findById(categoryId).get();
	}

	@Override
	public List<ProductCategory> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
		// TODO Auto-generated method stub
		return repository.findByCategoryTypeIn(categoryTypeList);
	}

	@Override
	public ProductCategory save(ProductCategory productCategory) {
		// TODO Auto-generated method stub
		return repository.save(productCategory);
	}

}
