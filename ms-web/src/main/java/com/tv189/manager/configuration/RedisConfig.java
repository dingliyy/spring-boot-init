package com.tv189.manager.configuration;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.tv189.common.util.Md5Util;

/**
 * Redis配置类
 * 
 * @author dingli
 * @date 2018年1月31日 下午5:26:06
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

	/**
	 * 申明缓存管理器，会创建一个切面（aspect）并触发Spring缓存注解的切点（pointcut）
	 * 根据类或者方法所使用的注解以及缓存的状态，这个切面会从缓存中获取数据，将数据添加到缓存之中或者从缓存中移除某个值
	 * 
	 * @return
	 */
	@Bean
	public RedisCacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager manager = new RedisCacheManager(redisTemplate);
		manager.setDefaultExpiration(1800);// 设置缓存默认过期时间1800秒
		return manager;
	}

	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method,
					Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					if (obj != null) {
						sb.append(obj.toString());
					}
				}
				return Md5Util.encodeStr(sb.toString());
			}
		};
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		// 创建一个模板类
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		// 将刚才的redis连接工厂设置到模板类中
		template.setConnectionFactory(factory);
		RedisSerializer<String> keySerializer = new StringRedisSerializer();
		template.setKeySerializer(keySerializer);
		RedisSerializer<Object> valueSerializer = new GenericJackson2JsonRedisSerializer();
		template.setValueSerializer(valueSerializer);
		return template;

	}
}
