package com.immoc.sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immoc.sell.dto.OrderDTO;
import com.immoc.sell.service.PayService;
import com.immoc.sell.utils.JsonUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PayServiceImpl implements PayService {
	@Autowired
	private BestPayService bestPayService; // import导入
	
	@Override
	public PayResponse create(OrderDTO orderDTO) {
		final String ORDER_NAME = "点餐测试";
		
		PayRequest payRequest = new PayRequest();
		payRequest.setOpenid(orderDTO.getBuyerOpenid());
		payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
		payRequest.setOrderId(orderDTO.getOrderId());
		payRequest.setOrderName(ORDER_NAME);
		payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
		
		// log.info("{}", payRequest);
		System.out.println("payRequest" + JsonUtil.toJson(payRequest));
		PayResponse payResponse = bestPayService.pay(payRequest);
		// logo
		System.out.println("payResponse" + JsonUtil.toJson(payResponse));
		return payResponse;
		
	}
}
