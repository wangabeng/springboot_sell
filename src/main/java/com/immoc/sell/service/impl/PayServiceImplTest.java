package com.immoc.sell.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.immoc.sell.dto.OrderDTO;
import com.immoc.sell.service.OrderService;
import com.immoc.sell.service.PayService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {
	@Autowired
	private PayService payService;
	
	@Autowired
	private OrderService orderService;
	 
	@Test
	public void create () throws Exception {
		OrderDTO orderDTO = orderService.findOne("1564664460087302909");
		payService.create(orderDTO);
	}
	
	
}
