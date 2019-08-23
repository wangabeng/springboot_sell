package com.immoc.sell.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.immoc.sell.dto.OrderDTO;
import com.immoc.sell.service.OrderService;

@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {
	@Autowired
	private OrderService orderService;
	@GetMapping("/list")
	public ModelAndView list (@RequestParam (value="page", defaultValue="1") Integer page, 
			@RequestParam (value="size", defaultValue="10") Integer size,
			Map<String, Object> map) {
		// PageRequest request = new PageRequest(page - 1, size);
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<OrderDTO> orderDTOpage = orderService.findList(pageable);
		System.out.println("orderDTOpage所有内容:" + orderDTOpage.getContent());
		System.out.println("orderDTOpage所有页数:" + orderDTOpage.getTotalPages());
		map.put("orderDTOpage", orderDTOpage);
		map.put("page", page);
		map.put("size", size);
		return new ModelAndView("/order/list", map);
		
	}
}