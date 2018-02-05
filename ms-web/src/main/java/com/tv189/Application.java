package com.tv189;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@ServletComponentScan//扫描servlet组件，在druid数据源监控中会用到
@EnableCaching//启用Cache注解支持
@EnableDiscoveryClient // 启用注册与发现
public class Application {
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
}
