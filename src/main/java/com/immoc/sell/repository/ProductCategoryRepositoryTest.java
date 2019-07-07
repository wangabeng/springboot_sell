package com.immoc.sell.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.immoc.sell.dataobject.ProductCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
	@Autowired
	private ProductCategoryRepository repository;
		
	@Test
	public void findOneTest () {
		// Integer id = 1;
		ProductCategory productCategory = repository.findById(new Integer(1)).get(); // 2.0后的写法
		System.out.print(productCategory.toString());
	}
}
