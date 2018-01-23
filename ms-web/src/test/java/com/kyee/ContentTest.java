package com.kyee;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kyee.core.util.HttpHelper;

/**
 * 注解 @RunWith 添加SpringJunit支持，由此引入Spring-test框架支持
 * 由于是web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
 * 注解 @SpringBootTest 指定Spring Boot 工程的启动类Application
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
//@WebAppConfigurations
public class ContentTest {
	@Test
	public void search(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("keyword", "C123456");
		params.put("pageNumber", "0");
		params.put("pageSize", "10");
		String str = HttpHelper.doPost("http://127.0.0.1:8088/manager/content/search", params,"utf-8",false);
		log.info(str);
	}
}
