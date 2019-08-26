package com.immoc.sell.dataobject;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SellerInfo {
	
	@Id
	private String sellerId;
	
	private String username;
	
	private String password;
	
	private String openid;

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getUserName() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
