package com.immoc.sell.service.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.immoc.sell.dataobject.ProductCategory;



@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@Test
	public void findOne () throws Exception {
		ProductCategory productCategory = categoryService.findOne(8);
		Assert.assertEquals(new Integer(8), productCategory.getCategoryId());
	}
	
	@Test
	public void findAll () throws Exception {
		List<ProductCategory> productCategoryList = categoryService.findAll();
		Assert.assertNotEquals(0, productCategoryList.size());
	}
	
	@Test
	public void findCategoryTypeIn () throws Exception {
		List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(1,2,3,4));
		Assert.assertNotEquals(0, productCategoryList.size());
	}
	
	@Test
	public void save () throws Exception {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName("男生专项");
		productCategory.setCategoryType(new Integer(12));
		ProductCategory result = categoryService.save(productCategory);
		
		Assert.assertNotNull(result);
	}
}
