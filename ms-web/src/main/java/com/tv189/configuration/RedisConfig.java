package com.tv189.configuration;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import com.tv189.common.util.Md5Util;

/**
 * Redis配置类
 * @author dingli
 * @date 2018年1月31日 下午5:26:06
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport{
	
	/**
	 * 申明缓存管理器，会创建一个切面（aspect）并触发Spring缓存注解的切点（pointcut）
	 * 根据类或者方法所使用的注解以及缓存的状态，这个切面会从缓存中获取数据，将数据添加到缓存之中或者从缓存中移除某个值
	 
	 * @return
	 */
	@Bean
	public RedisCacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager manager = new RedisCacheManager(redisTemplate);
		manager.setDefaultExpiration(1800);//设置缓存默认过期时间1800秒
	    return manager;
	}
	
	@Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                	if(obj != null){
                		sb.append(obj.toString());
                	}
                }
                return Md5Util.encodeStr(sb.toString());
            }
        };
    }  


	@Bean
	public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
		// 创建一个模板类
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		// 将刚才的redis连接工厂设置到模板类中
		template.setConnectionFactory(factory);
		// 设置key的序列化器
//		template.setKeySerializer(new StringRedisSerializer());
		// 设置value的序列化器
		
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);//使用Jackson将对象序列化为JSON
//		ObjectMapper om = new ObjectMapper(); //json转对象类，不设置默认的会将json转成hashmap
//		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//		jackson2JsonRedisSerializer.setObjectMapper(om);
		
		JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
		template.setValueSerializer(jdkSerializationRedisSerializer);

		return template;
	}
}
