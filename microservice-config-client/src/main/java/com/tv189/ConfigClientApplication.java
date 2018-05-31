package com.tv189;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConfigClientApplication {
	
	@Bean
    public RequestInterceptor headerInterceptor() {
        return new RequestInterceptor() {
			@Override
			public void apply(feign.RequestTemplate requestTemplate) {
				  ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
	                        .getRequestAttributes();
	                HttpServletRequest request = attributes.getRequest();
	                Enumeration<String> headerNames = request.getHeaderNames();
	                if (headerNames != null) {
	                    while (headerNames.hasMoreElements()) {
	                        String name = headerNames.nextElement();
	                        String values = request.getHeader(name);
	                        requestTemplate.header(name, values);
	                    }
	                }
			}
        };
    }
	
	public static void main(String[] args){
		SpringApplication.run(ConfigClientApplication.class, args);
	}
}
