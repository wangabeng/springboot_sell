package com.immoc.sell.service;

import com.immoc.sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

public interface PayService {
	PayResponse create (OrderDTO orderDTO);

	PayResponse notify(String notifyData);
	
	RefundResponse refund(OrderDTO orderDTO);
}
