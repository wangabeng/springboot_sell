package com.immoc.sell.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.immoc.sell.VO.ResultVO;
import com.immoc.sell.dataobject.OrderMaster;
import com.immoc.sell.dto.OrderDTO;
import com.immoc.sell.repository.OrderDetailRepository;
import com.immoc.sell.repository.OrderMasterRepository;
import com.immoc.sell.service.OrderService;
import com.immoc.sell.utils.ResultVOUtil;


import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@RequestMapping("/buyer/test")
public class OrderTestController {
	@Autowired
	private OrderMasterRepository orderMasterRepository;

	@GetMapping("/list")
	public ResultVO testList() {
		OrderMaster orderMaster = orderMasterRepository.findById("1564552383257138468").get();

		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);

		// 日期转换
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		String dateString = formatter.format(orderDTO.getCreateTime());

		Map<String, HashMap> map = new HashMap<>();
		HashMap hashMap = new HashMap();
		map.put("orderId", hashMap);

		ResultVO resultVO = ResultVOUtil.success(map);

		return resultVO;
	}
	@GetMapping("/getcode")
	public String getCode (@RequestParam(value = "code") String code, @RequestParam(value = "state") String state) {
		String token = "";
		String openid = "";
		// 1 获取code
		
		// 2 获取code后，请求以下链接获取access_token：  https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
		
		// 发送get请求
		OkHttpClient client1 = new OkHttpClient();
		
		// String credential = Credentials.basic("USER", "PASSWORD");

		Request request = new Request.Builder()
				.url("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxdef38d7055ecb07a&secret=6c8115af3f12f100d3f9e0f45808e153&code=" + code  + "&grant_type=authorization_code")
				.get()
				.build();

		try (Response response = client1.newCall(request).execute()) {
			String secondreq =  response.body().string();
			JSONObject object = (JSONObject)JSONObject.parse(secondreq);
			token = object.getString("access_token");
			openid = object.getString("openid");
			System.out.print("secondreq"+ secondreq);
			
			// return secondreq;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 3 获取用户信息 http：GET（请使用https协议） https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
		OkHttpClient client2 = new OkHttpClient();
		
		// String credential = Credentials.basic("USER", "PASSWORD");

		Request request2 = new Request.Builder()
				.url(" https://api.weixin.qq.com/sns/userinfo?access_token=" + token + "&openid=" + openid + "&lang=zh_CN")
				.get()
				.build();

		try (Response response2 = client2.newCall(request).execute()) {
			String secondreq2 =  response2.body().string();
			// JSONObject object = (JSONObject)JSONObject.parse(secondreq);
			System.out.print("secondreq2:" + secondreq2);
			return secondreq2;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
		return "找不到";
		
	}
	
}
