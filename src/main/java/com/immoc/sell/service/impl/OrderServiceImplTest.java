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
	private final String ORDER_ID = "1563807175400352565";

	@Test
	public void create() throws Exception {
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

		OrderDetail o2 = new OrderDetail();
		o2.setProductId("123444");
		o2.setProductQuantity(3);
		orderDetailList.add(o2);

		orderDTO.setOrderDetailList(orderDetailList);

		OrderDTO result = orderService.create(orderDTO);
		System.out.print(result);
		// Assert.assertNotNull(result);
	}

	@Test
	public void findOne() {
		OrderDTO result = orderService.findOne(ORDER_ID);
		Assert.assertEquals(ORDER_ID, result.getOrderId());
	}
}
