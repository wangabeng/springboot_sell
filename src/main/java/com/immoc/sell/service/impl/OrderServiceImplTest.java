package com.immoc.sell.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.immoc.sell.dataobject.OrderDetail;
import com.immoc.sell.dto.OrderDTO;
import com.immoc.sell.enums.OrderStatusEnum;
import com.immoc.sell.enums.PayStatusEnum;
import com.immoc.sell.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
	private final String BUYER_OPENID = "11002001";
	private final String ORDER_ID = "1563947675903353172";

	@Autowired
	private OrderServiceImpl orderService;

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

	@Test
	public void findList() throws Exception {
		// PageRequest request = new PageRequest(0, 2);
		Pageable pageable = PageRequest.of(0, 2);
		Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, pageable);
		Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
	}

	@Test
	public void cancel() throws Exception {
		OrderDTO orderDTO = orderService.findOne(ORDER_ID);
		OrderDTO result = orderService.cancel(orderDTO);
		System.out.println(result);
		Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
	}
	
	@Test
	public void finish() throws Exception {
		OrderDTO orderDTO = orderService.findOne(ORDER_ID);
		OrderDTO result = orderService.finish(orderDTO);
		Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
	}
	
	@Test
	public void paid() throws Exception {
		OrderDTO orderDTO = orderService.findOne(ORDER_ID);
		OrderDTO result = orderService.paid(orderDTO);
		Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
	}
	
	//	卖家查询所有订单
	@Test
	public void list() throws Exception {
		// PageRequest request = new PageRequest(0, 2);
		Pageable pageable = PageRequest.of(0, 2);
		Page<OrderDTO> orderDTOPage = orderService.findList(pageable);
		Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
	}
	
}
