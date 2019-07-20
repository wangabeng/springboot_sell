package com.immoc.sell.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.immoc.sell.dataobject.OrderDetail;
import com.immoc.sell.dataobject.OrderMaster;
import com.immoc.sell.dataobject.ProductInfo;
import com.immoc.sell.dto.OrderDTO;
import com.immoc.sell.repository.OrderDetailRepository;
import com.immoc.sell.service.OrderService;
import com.immoc.sell.service.ProductService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private OrderMaster orderMaster;
	
	// 订单创建
	@Override
	@Transactional
	public OrderDTO create(OrderDTO orderDTO) {
		// TODO Auto-generated method stub
		BigDecimal orderAmount = new BigDecimal(0);
		// String orderId;
		
		// 1 查询商品的数量价格
		for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
			ProductInfo productInfo = productService.findOne(orderDetail.getProductIcon());
			if (productInfo == null) {
				// 需要定义异常及枚举类
				thorow new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			// 2 计算订单总价
			
		}
		
		return null;
	}

	@Override
	public OrderDTO findOne(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<OrderDTO> fidnList(String buyerOpenid, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO cancel(OrderDTO orderDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO finish(OrderDTO orderDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO paid(OrderDTO orderDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
