package com.immoc.sell.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immoc.sell.dataobject.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}
