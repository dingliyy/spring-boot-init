package com.tv189.consumer;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@RabbitListener 注解定义该类对 hello 队列的监听
@RabbitListener(queues = "hello")
@Component
@Slf4j
public class Receiver {
	
	//@RabbitHandler 注解来指定对消息处理的方法 
	@RabbitHandler
	public void process(String context){
		log.info("Receiver: " + context);
	}
}
