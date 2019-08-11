package com.immoc.sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immoc.sell.dto.OrderDTO;
import com.immoc.sell.service.PayService;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;

@Service
public class PayServiceImpl implements PayService {
	@Autowired
	private BestPayService bestPayService; // import导入
	
	@Override
	public void create(OrderDTO orderDTO) {
		PayRequest payRequest = new PayRequest();
		bestPayService.pay(payRequest);
	}
}
