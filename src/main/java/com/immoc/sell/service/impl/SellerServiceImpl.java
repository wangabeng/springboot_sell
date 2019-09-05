package com.immoc.sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immoc.sell.dataobject.SellerInfo;
import com.immoc.sell.repository.SellerInfoRepository;
import com.immoc.sell.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {
	@Autowired
	private SellerInfoRepository repository;
	@Override
	public SellerInfo findSellerInfoByOpenid (String openid) {
		return repository.findByOpenid(openid);
	}
}
