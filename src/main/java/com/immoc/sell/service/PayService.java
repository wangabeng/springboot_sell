package com.immoc.sell.service;

import com.immoc.sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;

public interface PayService {
	PayResponse create (OrderDTO orderDTO);
}
