package com.immoc.sell.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.immoc.sell.dataobject.ProductInfo;
import com.immoc.sell.dto.CartDTO;

public interface ProductService {
	ProductInfo findOne(String productId);
	List<ProductInfo> findUpAll();
	Page<ProductInfo> findAll(Pageable pageable);
	ProductInfo save(ProductInfo productInfo);
	// 增加库存
	void increaseStock(List<CartDTO> cartDTOList);
	
	// 减库存
	void decreaseStock(List<CartDTO> cartDTOList);
}
