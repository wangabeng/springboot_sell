package com.immoc.sell.repository;


import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.immoc.sell.dataobject.OrderDetail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OderDetailRepositoryTest {
	@Autowired
	private OderDetailRepository repository;
	
	@Test
	public void saveTest () {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setDetailId("34343443");
		orderDetail.setOrderId("111");
		orderDetail.setProductIcon("http://xxx");
		orderDetail.setProductId("3434343");
		orderDetail.setProductName("皮蛋粥2222");
		orderDetail.setProductPrice(new BigDecimal(5.5));
		orderDetail.setProductQuantity(2);
		
		OrderDetail result = repository.save(orderDetail);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void findByOrderId() throws Exception {
		List<OrderDetail> orderDetailList = repository.findByOrderId("111");
		Assert.assertNotEquals(0, orderDetailList.size());
	}
	
}
