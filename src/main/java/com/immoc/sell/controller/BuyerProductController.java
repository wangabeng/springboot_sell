package com.immoc.sell.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.immoc.sell.VO.ProductInfoVO;
import com.immoc.sell.VO.ProductVO;
import com.immoc.sell.VO.ResultVO;
import com.immoc.sell.dataobject.ProductCategory;
import com.immoc.sell.dataobject.ProductInfo;
import com.immoc.sell.service.CategoryService;
import com.immoc.sell.service.ProductService;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@GetMapping("/list")
	public ResultVO list() {
		// 查询所有商品 到一个商品集合中 ，然后把遍历此商品集合，把商品集合内每个元素的type值遍历出来，存放到一个Integer集合，然后一次性查找出所有的类目集合
		
		// 1 查询所有的上架商品
		List<ProductInfo> productInfoList = productService.findUpAll();
		
		// 2 查类目 一次性查
		List<Integer> categoryTypeList = new ArrayList();
		// 传统方法查
		for (ProductInfo productInfo: productInfoList) {
			categoryTypeList.add(productInfo.getCategoryType());
		}
		List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
		
		// 3 数据拼装
		List<ProductVO> productVOList = new ArrayList<>();
		for (ProductCategory productCategory: productCategoryList) {
			// 设置外层数据
			ProductVO productVO = new ProductVO();
			productVO.setCategoryType(productCategory.getCategoryType());
			productVO.setCategoryName(productCategory.getCategoryName());
			
			// 设置单个属性值位json集合
			List<ProductInfoVO> productInfoVOList = new ArrayList<>();
			// 遍历集合内的每个产品对象
			
			for (ProductInfo productInfo: productInfoList) {
				if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
					ProductInfoVO productInfoVO = new ProductInfoVO();
					BeanUtils.copyProperties(productInfo, productInfoVO);
					productInfoVOList.add(productInfoVO);
				}
			}
			productVO.setProductInfoVOList(productInfoVOList);
			productVOList.add(productVO);
		} 
		
		ResultVO resultVO = new ResultVO();
		resultVO.setCode(0);
		resultVO.setMsg("成功");
		resultVO.setData(productVOList);

		return resultVO;
	}
}