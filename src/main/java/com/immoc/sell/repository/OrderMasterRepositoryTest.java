package com.immoc.sell.repository;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.immoc.sell.dataobject.OrderMaster;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
	@Autowired
	private OrderMasterRepository repository;
	
	private final String OPENID = "2329039209";
	
	@Test
	public void saveTest () {
		OrderMaster orderMaster = new OrderMaster();
		orderMaster.setOrderId("2332323");
		orderMaster.setBuyerName("WANGwang");
		orderMaster.setBuyerPhone("34343234234");
		orderMaster.setBuyerAddress("湖南");
		orderMaster.setBuyerOpenid(OPENID);
		orderMaster.setOrderAmount(new BigDecimal(2.9));
		
		OrderMaster result = repository.save(orderMaster);
		
		Assert.assertNotNull(result);
	}
	
	@Test
	public  void findByBuyerOpenid () throws Exception {
		Pageable pageable = PageRequest.of(0,2);
		Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, pageable);
		System.out.print("分页" + result.getSize());
		Assert.assertNotEquals(0, result.getSize());
	}
}
