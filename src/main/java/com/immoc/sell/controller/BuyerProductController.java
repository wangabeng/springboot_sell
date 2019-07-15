package com.immoc.sell.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		
		ResultVO resultVO = new ResultVO();
		resultVO.setCode(0);
		resultVO.setMsg("成功");

		ProductInfoVO productInfoVO = new ProductInfoVO();

		ProductVO productVO = new ProductVO();
		productVO.setProductInfoVOList(Arrays.asList(productInfoVO, productInfoVO));

		resultVO.setData(Arrays.asList(productVO));

		return resultVO;
	}
}


/*
ProductInfo集合
[
	ProductInfo [productId=123444, productName=皮蛋粥, productPrice=3.20, productStock=100, productDescription=非常好喝的粥, productIcon=http://baidu.com/dddd, productStatus=0, categoryType=2], 
	ProductInfo [productId=5678, productName=虾米, productPrice=3.20, productStock=10, productDescription=虾虾, productIcon=http://baidu.com/, productStatus=0, categoryType=1]
]

ProductCategory集合
[
	ProductCategory [categoryId=1, categoryName=男生最好, categoryType=2], 
	ProductCategory [categoryId=8, categoryName=女生最爱2, categoryType=4], 
	ProductCategory [categoryId=9, categoryName=儿子最爱3, categoryType=3]
]*/
