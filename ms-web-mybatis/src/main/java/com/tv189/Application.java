package com.tv189;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan//扫描servlet组件，在druid数据源监控中会用到
@MapperScan("com.tv189.mapper")
public class Application {
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
}
