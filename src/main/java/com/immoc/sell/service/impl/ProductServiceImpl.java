package com.immoc.sell.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.immoc.sell.dataobject.ProductInfo;
import com.immoc.sell.enums.ProductStatusEnum;
import com.immoc.sell.repository.ProductInfoRepository;
import com.immoc.sell.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductInfoRepository repository;
	
	@Override
	public ProductInfo findOne(String productId) {
		// TODO Auto-generated method stub
		return repository.findById(productId).get();
	}

	@Override
	public List<ProductInfo> findUpAll() {
		// TODO Auto-generated method stub
		return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

	@Override
	public Page<ProductInfo> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return repository.findAll(pageable);
	}

	@Override
	public ProductInfo save(ProductInfo productInfo) {
		// TODO Auto-generated method stub
		return repository.save(productInfo);
	}

}