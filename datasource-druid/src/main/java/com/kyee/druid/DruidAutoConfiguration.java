package com.kyee.druid;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
@EnableConfigurationProperties(DruidProperties.class) // 开启对@ConfigurationProperties注解配置Bean的支持
@ConditionalOnClass(DruidDataSource.class)// 存在对应class文件时才解析
@ConditionalOnProperty(prefix = "druid", name = "username") //控制Configuration是否生效
@AutoConfigureBefore(DataSourceAutoConfiguration.class)//加载顺序
public class DruidAutoConfiguration {
	@Autowired
    private DruidProperties properties;
	
	@Value("${druid.username}")
	private String username;
	
	@Value("${druid.password}")
	private String password;
	
	@Value("${druid.logSlowSql}")
	private String logSlowSql;

    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        if(properties.getTimeBetweenEvictionRunsMillis() > 0){
        	dataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        }
        if (properties.getInitialSize() > 0) {
            dataSource.setInitialSize(properties.getInitialSize());
        }
        if (properties.getMinIdle() > 0) {
            dataSource.setMinIdle(properties.getMinIdle());
        }
        if (properties.getMaxActive() > 0) {
            dataSource.setMaxActive(properties.getMaxActive());
        }
        if(properties.getMinEvictableIdleTimeMillis() > 0){
        	dataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        }
        if(properties.getValidationQuery() != null && ! properties.getValidationQuery().equals("")){
        	dataSource.setValidationQuery(properties.getValidationQuery());
        }
        if(properties.getFilters() != null && ! properties.getFilters().equals("")){
        	dataSource.setFilters(properties.getFilters());
        }
        if(properties.getConnectionProperties() != null && ! properties.getConnectionProperties().equals("")){
        	dataSource.setConnectionProperties(properties.getConnectionProperties());
        }
        dataSource.setDefaultAutoCommit(properties.isDefaultAutoCommit());
        dataSource.setUseGlobalDataSourceStat(properties.isUseGlobalDataSourceStat());
        dataSource.setTestOnBorrow(properties.isTestOnBorrow());
        dataSource.init();
        return dataSource;
    }
    
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", username);
        reg.addInitParameter("loginPassword", password);
        reg.addInitParameter("logSlowSql", logSlowSql);
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

}
