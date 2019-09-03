package com.immoc.sell;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

// 允许跨域请求 全局设置
@CrossOrigin(origins = {"*", "null"})
@SpringBootApplication
public class SellApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellApplication.class, args);
	}

	@Bean
	  public TomcatServletWebServerFactory webServerFactory() {
	     TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
	     factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
	              @Override
	              public void customize(Connector connector) {
	                  connector.setProperty("relaxedPathChars", "\"<>[\\]^`{|}");
	                  connector.setProperty("relaxedQueryChars", "\"<>[\\]^`{|}");
	               }
	      });
	      return factory;
	  }
}
