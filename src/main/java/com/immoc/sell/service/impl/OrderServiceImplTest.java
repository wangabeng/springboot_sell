package com.immoc.sell.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.immoc.sell.dataobject.OrderDetail;
import com.immoc.sell.dto.OrderDTO;
import com.immoc.sell.service.OrderService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
	@Autowired
	private OrderServiceImpl orderService;
	
	private final String BUYER_OPENID = "11002001";
	
	@Test
	public void create () throws Exception {
		// 获取买家信息 填充到orderDTO
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerName("WANG蹦");
		orderDTO.setBuyerAddress("浙江 ");
		orderDTO.setBuyerPhone("1222233");
		orderDTO.setBuyerOpenid(BUYER_OPENID);
		
		// 购物车 模拟前端获取
		List<OrderDetail> orderDetailList = new ArrayList<>();
		OrderDetail o1 = new OrderDetail(); 
		o1.setProductId("111");
		o1.setProductQuantity(2);
		orderDetailList.add(o1);
		
		orderDTO.setOrderDetailList(orderDetailList);
		
		OrderDTO result = orderService.create(orderDTO);
		Assert.assertNotNull(result);
	}
}
