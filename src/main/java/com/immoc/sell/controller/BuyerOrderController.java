package com.immoc.sell.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.immoc.sell.VO.ResultVO;
import com.immoc.sell.converter.OrderForm2OrderDTOConverter;
import com.immoc.sell.dto.OrderDTO;
import com.immoc.sell.enums.ResultEnum;
import com.immoc.sell.exception.SellException;
import com.immoc.sell.form.OrderForm;
import com.immoc.sell.service.OrderService;
import com.immoc.sell.utils.ResultVOUtil;

@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {
	@Autowired
	private OrderService orderService;
	
	// 创建订单
	@PostMapping("/create")
	public ResultVO<Map<String, String>> create (@Valid OrderForm orderForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// 打印日志
			throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
		}
		// form转成orderDTO
		OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
		if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
			// 购物车不能为空
			throw new SellException(ResultEnum.CART_EMPTY);
		}
		// 创建订单
		OrderDTO createResult = orderService.create(orderDTO);
		
		Map<String, String> map = new HashMap<>();
		map.put("orderId", createResult.getOrderId());
		
		// 返回json数据到前端
		
		
		return ResultVOUtil.success(map);
	}
	
	// 订单列表
	@GetMapping("/list")
	public ResultVO<List<OrderDTO>> list (
			@RequestParam("openid")String openid,
			@RequestParam(value = "page", defaultValue="0")Integer page,
			@RequestParam(value = "size", defaultValue="10")Integer size
			) {
		if (StringUtils.isEmpty(openid)) {
			// 打印日志
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		PageRequest request = PageRequest.of(page, size);
		Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);
		
		return ResultVOUtil.success(orderDTOPage.getContent());
	}
}
