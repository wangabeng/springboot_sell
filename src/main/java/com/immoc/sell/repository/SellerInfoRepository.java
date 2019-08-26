package com.immoc.sell.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immoc.sell.dataobject.SellerInfo;

public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
	SellerInfo findByOpenid(String openid);
}
