package com.immoc.sell.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.immoc.sell.dataobject.ProductInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
	@Autowired
	private ProductServiceImpl productService;
	@Test
	public void findOne() throws Exception {
		ProductInfo productInfo = productService.findOne("123444");
		Assert.assertEquals("123444", productInfo.getProductId());
	}
	
	@Test
	public void findUpAll() throws Exception {
		List<ProductInfo> productInfoList = productService.findUpAll();
		Assert.assertNotEquals(0, productInfoList.size());
	}
	
	@Test
	public void findAll() throws Exception {
		Sort sort = new Sort(Sort.Direction.DESC, "productId");
		Pageable pageable = PageRequest.of(0,2,sort); 
		Page<ProductInfo> productInfoPage = productService.findAll(pageable);
		System.out.println("page tab:" + productInfoPage.getTotalElements());
		Assert.assertNotEquals(0, productInfoPage.getTotalElements());
	}
	
	@Test
	public void save () {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId("5678");
		productInfo.setProductName("虾米");
		productInfo.setProductPrice(new BigDecimal(3.2));
		productInfo.setProductStock(10);
		productInfo.setProductDescription("虾虾");
		productInfo.setProductIcon("http://baidu.com/");
		productInfo.setProductStatus(0);
		productInfo.setCategoryType(1);
		
		ProductInfo result = productService.save(productInfo);
		Assert.assertNotNull(result);
		
	}
}
