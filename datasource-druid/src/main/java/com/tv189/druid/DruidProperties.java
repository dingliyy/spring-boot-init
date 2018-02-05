package com.tv189.druid;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.datasource")//自动匹配配置文件中spring.datasource为前缀的属性
public class DruidProperties {
	private String url;
    private String username;
    private String password;
    private String driverClass;
    private int maxActive;//最大连接数
    private int minIdle;//最小连接数
    private int initialSize;//初始化数量
    private boolean testOnBorrow;
    private Long timeBetweenEvictionRunsMillis;//心跳
    private Long minEvictableIdleTimeMillis;//配置一个连接在池中最小生存的时间，单位是毫秒 
    private String validationQuery;
    private String filters;//配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙 
    private String connectionProperties;//通过connectProperties属性来打开mergeSql功能，慢SQL记录
    private boolean useGlobalDataSourceStat;//合并多个DruidDataSource的监控数据
    private boolean defaultAutoCommit;
    
}
