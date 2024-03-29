package com.immoc.sell.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.immoc.sell.dataobject.SellerInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {
	private static final String openid = "abc";
	@Autowired
	private SellerServiceImpl sellerService;
	
	@Test
	public void findSellerByOpenid () throws Exception {
		SellerInfo result = sellerService.findSellerInfoByOpenid(openid);
		Assert.assertEquals(openid, result.getOpenid());
	}
}
