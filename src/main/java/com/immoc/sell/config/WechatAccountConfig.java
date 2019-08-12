package com.immoc.sell.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
	private String mpAppId;
	private String mpAppSecret;
	//商户号
	private String mchId;
	// 商户秘钥
	private String mchKey;
	// 商户证书路径
	private String keyPath;
	// 预支付订单生成或异步通知地址
	private String notifyUrl;
	
	public String getMpAppId() {
		return mpAppId;
	}
	public void setMpAppId(String mpAppId) {
		this.mpAppId = mpAppId;
	}
	public String getMpAppSecret() {
		return mpAppSecret;
	}
	public void setMpAppSecret(String mpAppSecret) {
		this.mpAppSecret = mpAppSecret;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getMchKey() {
		return mchKey;
	}
	public void setMchKey(String mchKey) {
		this.mchKey = mchKey;
	}
	public String getKeyPath() {
		return keyPath;
	}
	public void setKeyPath(String keyPath) {
		this.keyPath = keyPath;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
}
