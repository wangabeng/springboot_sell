package com.immoc.sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immoc.sell.dto.OrderDTO;
import com.immoc.sell.enums.ResultEnum;
import com.immoc.sell.exception.SellException;
import com.immoc.sell.service.OrderService;
import com.immoc.sell.service.PayService;
import com.immoc.sell.utils.JsonUtil;
import com.immoc.sell.utils.MathUtil;
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
	
	@Autowired
	private OrderService orderService;
	
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
	
	@Override
	public PayResponse notify (String notifyData) {
		PayResponse payResponse = bestPayService.asyncNotify(notifyData);
		// 打印异步通知
		System.out.println("payResponse:" + payResponse);
		System.out.println("PayResponse Json:" + JsonUtil.toJson(payResponse));
		
		// 1 验证签名（SDK已经验证）
		// 2 支付状态验证
		// 3 支付金额校验
		// 4 支付人校验（下单人==支付人）本项目不限制
		
		// 3 支付金额校验
		// 查询订单
		OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
		
		// 判断订单是否存在
		if (orderDTO == null) {
			// 打印日志  
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);			
		}
		
		// 判断金额是否一致
		if (!MathUtil.equals(payResponse.getOrderAmount(), orderDTO.getOrderAmount().doubleValue())) {
			// 打印异步通知日志 订单金额不一致
			throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
		}
		
		// 修改订单支付状态
		orderService.paid(orderDTO);
		
		
		return payResponse;
		
	}
}
