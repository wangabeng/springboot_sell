package com.immoc.sell.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.immoc.sell.VO.ResultVO;
import com.immoc.sell.dataobject.OrderMaster;
import com.immoc.sell.dto.OrderDTO;
import com.immoc.sell.repository.OrderDetailRepository;
import com.immoc.sell.repository.OrderMasterRepository;
import com.immoc.sell.service.OrderService;
import com.immoc.sell.utils.ResultVOUtil;

@RestController
@RequestMapping("/buyer/test")
public class OrderTestController {
	@Autowired
	private OrderMasterRepository orderMasterRepository;

	@GetMapping("/list")
	public ResultVO testList() {
		OrderMaster orderMaster = orderMasterRepository.findById("1564552383257138468").get();

		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);

		// 日期转换
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		String dateString = formatter.format(orderDTO.getCreateTime());

		Map<String, HashMap> map = new HashMap<>();
		HashMap hashMap = new HashMap();
		map.put("orderId", hashMap);

		ResultVO resultVO = ResultVOUtil.success(map);

		return resultVO;
	}
	@GetMapping("/getcode")
	public String getCode () {
		return "good";
		
	}
	
}
