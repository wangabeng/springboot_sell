package com.immoc.sell.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.immoc.sell.dataobject.SellerInfo;
import com.immoc.sell.repository.SellerInfoRepository;
import com.immoc.sell.utils.KeyUtil;



@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {
	@Autowired
	private SellerInfoRepository repository;
	
	@Test
	public void save () {
		SellerInfo sellerInfo = new SellerInfo();
		sellerInfo.setSellerId(KeyUtil.genUniqueKey());
		sellerInfo.setUsername("admin");
		sellerInfo.setPassword("abc");
		sellerInfo.setOpenid("abc");
		
		SellerInfo result = repository.save(sellerInfo);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void findByOpenid () throws Exception{
		SellerInfo result = repository.findByOpenid("abc");

		Assert.assertEquals("abc", result.getOpenid());
	}
}
