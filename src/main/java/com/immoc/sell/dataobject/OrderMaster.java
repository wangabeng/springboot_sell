package com.immoc.sell.dataobject;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import com.immoc.sell.enums.OrderStatusEnum;
import com.immoc.sell.enums.PayStatusEnum;

@Entity
@DynamicUpdate
public class OrderMaster {
	@Id
	private String orderId;
	
	private String buyerName;
	
	private String buyerPhone;
	
	private String buyerAddress;
	
	private String buyerOpenid;
	
	private BigDecimal orderAmount;
	
	private Integer orderStatus = OrderStatusEnum.NEW.getCode();
	
	private Integer payStatus = PayStatusEnum.WAIT.getCode();

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBuyderName() {
		return buyerName;
	}

	public void setBuyderName(String buyderName) {
		this.buyerName = buyderName;
	}

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public String getBuyderAddress() {
		return buyerAddress;
	}

	public void setBuyderAddress(String buyderAddress) {
		this.buyerAddress = buyderAddress;
	}

	public String getBuyderOpenid() {
		return buyerOpenid;
	}

	public void setBuyderOpenid(String buyderOpenid) {
		this.buyerOpenid = buyderOpenid;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	@Override
	public String toString() {
		return "OrderMaster [orderId=" + orderId + ", buyderName=" + buyerName + ", buyerPhone=" + buyerPhone
				+ ", buyderAddress=" + buyerAddress + ", buyderOpenid=" + buyerOpenid + ", orderAmount=" + orderAmount
				+ ", orderStatus=" + orderStatus + ", payStatus=" + payStatus + "]";
	}
	
	
}
