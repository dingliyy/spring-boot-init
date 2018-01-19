package com.kyee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kyee.provider.Sender;

/**
 * 注解 @RunWith 添加SpringJunit支持，由此引入Spring-test框架支持
 * 由于是web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
 * 注解 @SpringBootTest 指定Spring Boot 工程的启动类Application
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
//@WebAppConfiguration
public class HelloApplicationTests {
	@Autowired
	private Sender sender;
	
	@Test
	public void hello() throws Exception {
		sender.send();
	}
}
