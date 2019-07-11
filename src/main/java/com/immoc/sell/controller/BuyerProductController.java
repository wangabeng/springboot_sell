package com.immoc.sell.controller;

import org.assertj.core.util.Arrays;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.immoc.sell.VO.ProductInfoVO;
import com.immoc.sell.VO.ProductVO;
import com.immoc.sell.VO.ResultVO;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
	@GetMapping("/list")
	public ResultVO list () {
		ResultVO resultVO = new ResultVO();
		resultVO.setCode(0);
		resultVO.setMsg("成功");
		
		
		ProductInfoVO productInfoVO = new ProductInfoVO();
				
		ProductVO productVO = new ProductVO();
		// productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
		
		return resultVO;
	}
}
