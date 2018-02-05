package com.tv189;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 使用@EnableZuulProxy注解开启Zuul的API网关服务功能，也可以使用@EnableZuulServer注解开启。
 * EnableZuulProxy是EnableZuulServer的增强版。
 * 
 * 通过在yml中配置服务路由完成路由转发，例如：
 * zuul.routes.api-a.path=/api-a/**
 * zuul.routes.api-b.serviceId=hello-service
 * 
 * http://localhost:8762/api-a/hello 该url符合/api-a/**规则，由api-a路由负责转发，
 * 该路由映射的serviceId为hello-service,所以最终/hello 请求会被发送到hello-service服务的某个实例上去
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GatewayServerApplication {
	public static void main(String[] args){
		SpringApplication.run(GatewayServerApplication.class, args);
	}
}
