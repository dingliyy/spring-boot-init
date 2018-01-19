package com.kyee.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 的配置类
 * 用来配置队列、交换器、路由等高级信息
 */
@Configuration
public class RabbitmqConfig {
	
	@Bean
	public Queue helloQueue(){
		return new Queue("hello");
	}
}
