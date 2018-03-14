package com.tv189.zookeeper.zk;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "zookeeper.config")//自动匹配配置文件中zookeeper.config为前缀的属性
public class ZkProperties {
	/**
	 * zookeeper集群地址
	 */
	private String address;
	
	/**
	 * 访问路径
	 */
	private String dataDir;
	
	/**
	 * 	初始sleep时间
	 */
	private Integer baseSleepTimeMs;
	
	/**
	 * 连接最大重试次数
	 */
	private Integer maxRetries;
}
