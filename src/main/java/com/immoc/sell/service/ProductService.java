package com.immoc.sell.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.immoc.sell.dataobject.ProductInfo;

public interface ProductService {
	ProductInfo findOne(String productId);
	List<ProductInfo> findAll();
	Page<ProductInfo> findAll(Pageable pageable);
	ProductInfo save(ProductInfo productInfo);
}
