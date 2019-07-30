package com.immoc.sell.converter;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.immoc.sell.dataobject.OrderDetail;
import com.immoc.sell.dto.OrderDTO;
import com.immoc.sell.enums.ResultEnum;
import com.immoc.sell.exception.SellException;
import com.immoc.sell.form.OrderForm;

public class OrderForm2OrderDTOConverter {
	public static OrderDTO convert (OrderForm orderForm) {
		Gson gson = new Gson();
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerName(orderForm.getName());
		orderDTO.setBuyerPhone(orderForm.getPhone());
		orderDTO.setBuyerAddress(orderForm.getAddress());
		orderDTO.setBuyerOpenid(orderForm.getOpenid());

		// 转购物车
		List<OrderDetail> orderDetailList = new ArrayList<>();
		try {
			orderDetailList = gson.fromJson(orderForm.getItems(),
				new TypeToken<List<OrderDetail>>() {}.getType());
		} catch (Exception e) {
			// 打印日志
			throw new SellException(ResultEnum.PARAM_ERROR);
		}

		orderDTO.setOrderDetailList(orderDetailList);
		return orderDTO;
	}
}
