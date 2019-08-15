package com.immoc.sell.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.immoc.sell.dto.OrderDTO;
import com.immoc.sell.enums.ResultEnum;
import com.immoc.sell.exception.SellException;
import com.immoc.sell.service.OrderService;
import com.immoc.sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;

@Controller
@RequestMapping("/pay")
public class PayController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private PayService payService;

	@GetMapping("/create")
	public ModelAndView create(@RequestParam("orderId") String orderId, 
			@RequestParam("returnUrl") String returnUrl,
			Map<String, Object> map) {
		// 1 查询订单
		OrderDTO orderDTO = orderService.findOne(orderId);
		if (orderDTO == null) {
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}

		// 2 发起支付(调用统一下单API)
		PayResponse payResponse = payService.create(orderDTO);

		System.out.println("将要跳转");
		// 然后把统一下单返回的数据返回到前端 以供支付
		map.put("payResponse", payResponse);
		map.put("returnUrl", returnUrl);
		return new ModelAndView("pay/create", map);
	}
}
