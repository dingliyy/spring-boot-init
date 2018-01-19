package com.kyee.provider;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Sender {
	/**
	 * AmqpTemplate 接口定义了一套针对 AMQP 协议的基础操作。在spring Boot中会根据配置来注入其具体实现。
	 * AMQP 是Advanced Message Queuing Protocol 的简称，它是面向消息中间件的开放式标准应用层协议。
	 * AMPQ 与 JMS 不同，JMS 定义了一个API和一组消息收发必须实现的行为，而 AMQP 是一个线路级协议。
	 * 线路级协议描述 的是通过网络发送的数据传输格式。因此，任何符合该数据格式的消息发送和接收工具都能互相兼容和进行操作，这样就能轻易实现跨技术平台的构架方案。
	 */
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	public void send(){
		String context = "hello " + new Date();
		log.info("Sender: " + context);
		
		//产生一个字符串，并发送到名为 hello 的队列中
		this.rabbitTemplate.convertAndSend("hello", context);
	}
}
