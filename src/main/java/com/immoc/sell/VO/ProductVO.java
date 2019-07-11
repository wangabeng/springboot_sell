package com.immoc.sell.VO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductVO {
	@JsonProperty("name")
	private String categoryName;
	@JsonProperty("type")
	private String categoryType;
	@JsonProperty("food")
	private List<ProductInfoVO> productInfoVOList;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public List<ProductInfoVO> getProductInfoVOList() {
		return productInfoVOList;
	}
	public void setProductInfoVOList(List<ProductInfoVO> productInfoVOList) {
		this.productInfoVOList = productInfoVOList;
	}
	
}
