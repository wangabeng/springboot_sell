package com.immoc.sell.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.immoc.sell.dataobject.ProductInfo;

public interface ProductService {
	ProductInfo findOne(String productId);
	List<ProductInfo> findUpAll();
	Page<ProductInfo> findAll(Pageable pageable);
	ProductInfo save(ProductInfo productInfo);
}
