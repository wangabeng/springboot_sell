package com.immoc.sell.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//新增加一个类用来添加虚拟路径映射
@Configuration
public class MyPicConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("config启动了");
        //项目路径
        //String savePath = "\\src\\main\\resources\\static\\upload\\";
        //String path = System.getProperty("user.dir")+savePath;
        //本地路径
        String path = "F:/springboot_sell/src/main/resources/static/article/";
        registry.addResourceHandler("/article/**").addResourceLocations("file:"+path);
    }
}