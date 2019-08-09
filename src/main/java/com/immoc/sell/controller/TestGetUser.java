package com.immoc.sell.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//测试是否获得openid  输入 http://nicedevelop.nat300.top/wechat/authorize?returnUrl=http://nicedevelop.nat300.top/getuser/test
@RestController
@RequestMapping("/getuser")
public class TestGetUser {
	@GetMapping("/test")
	public String test (@RequestParam("openid") String openid) {
		return openid;
	}
}
