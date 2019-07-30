package com.immoc.sell.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.immoc.sell.converter.OrderMasterToOrderDTOConverter;
import com.immoc.sell.dataobject.OrderDetail;
import com.immoc.sell.dataobject.OrderMaster;
import com.immoc.sell.dataobject.ProductInfo;
import com.immoc.sell.dto.CartDTO;
import com.immoc.sell.dto.OrderDTO;
import com.immoc.sell.enums.OrderStatusEnum;
import com.immoc.sell.enums.PayStatusEnum;
import com.immoc.sell.enums.ResultEnum;
import com.immoc.sell.exception.SellException;
import com.immoc.sell.repository.OrderDetailRepository;
import com.immoc.sell.repository.OrderMasterRepository;
import com.immoc.sell.service.OrderService;
import com.immoc.sell.service.ProductService;
import com.immoc.sell.utils.KeyUtil;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private ProductService productService;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private OrderMasterRepository orderMasterRepository;

	// 订单创建
	@Override
	@Transactional
	public OrderDTO create(OrderDTO orderDTO) {
		// TODO Auto-generated method stub
		BigDecimal orderAmount = new BigDecimal(0);
		String orderId = KeyUtil.genUniqueKey();
		List<CartDTO> cartDTOList = new ArrayList<>();

		// 1 查询商品的数量价格
		for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
			System.out.println("进入for循环" + orderDetail);
			ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
			System.out.println("productInfo值：" + productInfo);
			if (productInfo == null) {
				// 需要定义异常及枚举类
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			// 2 计算订单总价
			orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
					.add(orderAmount);

			// 订单详情入库
			BeanUtils.copyProperties(productInfo, orderDetail);
			// orderDetail.setDetailId(orderId); // 如果这样写，oder_detail表的主键primary
			// key为detail_id,
			// 如果一次主订单购买2个产品，就会产生2条订单详情记录，这两条order_detail记录的order_id是for循环外生成的，如果orderDetail.setDetailId(orderId)设置的detail_id也用这个for循环外层生成的orderId，就会因为主键冲突，导致无法同时插入这两条数据
			orderDetail.setDetailId(KeyUtil.genUniqueKey()); // 这样写才可以
			orderDetail.setOrderId(orderId);

			orderDetailRepository.save(orderDetail);
			System.out.println("入库后的订单详情orderDetail：" + orderDetail);

			// 创建购物车列表
			CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
			cartDTOList.add(cartDTO);
		}

		// 3 写入订单库
		OrderMaster orderMaster = new OrderMaster();
		orderDTO.setOrderId(orderId);
		BeanUtils.copyProperties(orderDTO, orderMaster);
		// orderMaster.setOrderId(orderId);
		orderMaster.setOrderAmount(orderAmount);
		orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
		orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

		orderMasterRepository.save(orderMaster);

		// 扣库存
		productService.decreaseStock(cartDTOList);

		return null;
	}

	@Override
	public OrderDTO findOne(String orderId) {
		// TODO Auto-generated method stub
		OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
		if (orderMaster == null) {
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
		if (CollectionUtils.isEmpty(orderDetailList)) {
			throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
		}

		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		orderDTO.setOrderDetailList(orderDetailList);

		return orderDTO;
	}

	@Override
	public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
		List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter.convert(orderMasterPage.getContent());
		Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable,
				orderMasterPage.getTotalElements());
		return orderDTOPage;
	}

	@Override
	public OrderDTO cancel(OrderDTO orderDTO) {
		// TODO Auto-generated method stub
		OrderMaster orderMaster = new OrderMaster();
		// 首先判断订单状态
		if (orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
			// 打印日志
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}

		// 修改订单状态
		orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updateResult = orderMasterRepository.save(orderMaster);
		if (updateResult == null) {
			// 打印日志
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}

		// 返回库存
		if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
			// 打印日志
			throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
		}
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
				.map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
		productService.increaseStock(cartDTOList);

		// 如果已经支付 需要退款
		if (!orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
			// TODO
		}

		return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO finish(OrderDTO orderDTO) {
		// TODO Auto-generated method stub
		// 判断订单状态 订单完结 只针对新订单
		if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
			// 打印日志
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR); // 订单状态不正确
		}
		// 修改订单状态
		orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updateResult = orderMasterRepository.save(orderMaster);
		if (updateResult == null) {
			// 打印日志
			throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
		}

		return orderDTO;
	}

	@Override
	public OrderDTO paid(OrderDTO orderDTO) {
		// TODO Auto-generated method stub
		// 判断订单状态
		if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
			// 打印日志
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR); // 订单状态不正确
		}

		// 判断支付状态
		if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
			// 打印日志
			throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR); // 订单状态不正确
		}
		// 修改支付状态
		orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updateResult = orderMasterRepository.save(orderMaster);
		if (updateResult == null) {
			// 打印日志
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}

		return orderDTO;
	}

}
