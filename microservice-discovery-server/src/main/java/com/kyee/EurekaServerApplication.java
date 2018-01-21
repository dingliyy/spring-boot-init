package com.kyee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注解 @SpringBootApplication 是一个组合注解，它整合了 @Configuration、 @EnableAutoConfiguration
 * 和 @ComponentScan 注解，并开启了Spring Boot 程序的组件扫描和自动配置功能。
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
	public static void main(String[] args){
		SpringApplication.run(EurekaServerApplication.class, args);
	}
}
