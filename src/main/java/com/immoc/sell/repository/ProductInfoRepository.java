package com.immoc.sell.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immoc.sell.dataobject.ProductInfo;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
	
	List<ProductInfo> findByProductStatus(Integer productStatus);
}
