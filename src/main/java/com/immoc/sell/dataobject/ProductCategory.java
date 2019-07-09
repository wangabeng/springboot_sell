package com.immoc.sell.dataobject;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name="product_category")
@DynamicUpdate
public class ProductCategory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer categoryId;
	private String categoryName;
	private Integer categoryType;
	private Date createTime;
	private Date updateTime;
	
	public ProductCategory() {
		// super();
		// TODO Auto-generated constructor stub
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}
	@Override
	public String toString() {
		return "ProductCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryType="
				+ categoryType + "]";
	}
	
}
