package com.tv189;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 通过EnableConfigServer注解来激活配置服务
 * 通过EnableDiscoveryClient来将配置服务注册到注册中心，让其他服务可发现、可使用配置服务
 * 
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerApplication {
	public static void main(String[] args){
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
