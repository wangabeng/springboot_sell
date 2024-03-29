package com.immoc.sell.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.immoc.sell.enums.ResultEnum;
import com.immoc.sell.exception.SellException;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
 
    @Autowired
    private WxMpService wxMpService;
 
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException{
        String url = "http://nicedevelop.nat300.top/wechat/userInfo";
        String redirectURL = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl, "UTF-8"));
        // log.info("【微信网页授权】获取code,redirectURL={}", redirectURL);
        return "redirect:" + redirectURL;
    }
 
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl) throws Exception {
        // log.info("【微信网页授权】code={}", code);
        // log.info("【微信网页授权】state={}", returnUrl);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            // log.info("【微信网页授权】{}", e);
        	// 把错误往外抛
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        System.out.print("wxMpOAuth2AccessToken:" + wxMpOAuth2AccessToken);
        String openId = wxMpOAuth2AccessToken.getOpenId();
        // log.info("【微信网页授权】openId={}", openId);
        // 拿到opneid后重定向
        return "redirect:" + returnUrl + "?openid=" + openId;
    }

    //测试是否获得openid  输入 http://nicedevelop.nat300.top/wechat/authorize?returnUrl=http://nicedevelop.nat300.top/wechat/testOpenid
    @RequestMapping("testOpenid")
    public String testOpenid(@RequestParam("openid")String openid){
        // log.info("获得用户的openid为:{}",openid);
    	System.out.print("openid:" + openid);
    	return openid;
    }
}