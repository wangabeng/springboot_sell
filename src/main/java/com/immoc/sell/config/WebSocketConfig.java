package com.immoc.sell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Component
public class WebSocketConfig {
  @Bean
  public ServerEndpointExporter serverEndPointExporter () {
    return new ServerEndpointExporter(); // 返回到AOC中
  }
}
