package com.immoc.sell.dataobject;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.immoc.sell.enums.OrderStatusEnum;
import com.immoc.sell.enums.PayStatusEnum;

@Entity
@Table(name="order_master")
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

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public String getBuyerOpenid() {
		return buyerOpenid;
	}

	public void setBuyerOpenid(String buyerOpenid) {
		this.buyerOpenid = buyerOpenid;
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
		return "OrderMaster [orderId=" + orderId + ", buyerName=" + buyerName + ", buyerPhone=" + buyerPhone
				+ ", buyerAddress=" + buyerAddress + ", buyerOpenid=" + buyerOpenid + ", orderAmount=" + orderAmount
				+ ", orderStatus=" + orderStatus + ", payStatus=" + payStatus + "]";
	}

	public OrderMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
