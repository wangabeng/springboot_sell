package com.immoc.sell.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immoc.sell.dataobject.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
	// 传入一个integer集合 查询相关类目
	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
