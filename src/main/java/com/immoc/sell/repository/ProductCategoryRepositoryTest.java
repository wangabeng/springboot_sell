package com.immoc.sell.repository;


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
public class ProductCategoryRepositoryTest {
	@Autowired
	private ProductCategoryRepository repository;
		
	@Test
	public void findOneTest () {
		// Integer id = 1;
		ProductCategory productCategory = repository.findById(new Integer(1)).get(); // 2.0后的写法
		System.out.print(productCategory.toString());
	}
	
	/*@Test
	public void saveTest () { // 测试插入数据
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName("儿子最爱3");
		productCategory.setCategoryType(new Integer(3));
		System.out.print("插入是否成功 duanyan");
		
		// 断言测试
		ProductCategory result = repository.save(productCategory);
		
		Assert.assertNotEquals(null, result);
	}*/
	
	/*@Test
	public void udateTest () { // 测试更新数据
		// 先查找
		ProductCategory productCategory = repository.findById(new Integer(1)).get();
		
		productCategory.setCategoryName("男生最好");
		repository.save(productCategory);
	}*/
	
	@Test
	public void findByCategoryTypeInTest () { // 测试更新数据
		
		List<Integer> list = Arrays.asList(2, 3, 4, 5);
		List<ProductCategory> result = repository.findByCategoryTypeIn(list);
		System.out.println(result.size() + "result.size()");
		// 集合元素大于0 就成功
		Assert.assertNotEquals(0, result.size());
	}
}
