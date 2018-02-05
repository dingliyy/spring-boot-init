package com.tv189;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer //启用监控服务
@EnableDiscoveryClient //启用注册与发现的客户端侧
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
