package com.immoc.sell.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.immoc.sell.dataobject.OrderMaster;
import com.immoc.sell.dto.OrderDTO;

public class OrderMasterToOrderDTOConverter {
	public static OrderDTO convert (OrderMaster orderMaster) {
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		return orderDTO;
	}
	public static List<OrderDTO> convert (List<OrderMaster> orderMasterList) {
		// 此处也可以用lambda表达式
		List<OrderDTO> orderDTOList = new ArrayList<>(); 
		for (OrderMaster orderMaster: orderMasterList) {
			orderDTOList.add(convert(orderMaster));
		}
		return orderDTOList;
	}
}
