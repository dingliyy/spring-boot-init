package com.tv189.manager;

import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.tv189.common.util.CacheInvocation;
import com.tv189.common.util.CompressUtil;

/**
 * Redis缓存处理通用类
 * 
 * ValueOperations：简单K-V操作
 * SetOperations：set类型数据操作
 * ZSetOperations：zset类型数据操作
 * HashOperations：针对map类型的数据操作
 * ListOperations：针对list类型的数据操作
 *
 * 提供了对key的“bound”(绑定)便捷化操作API，可以通过bound封装指定的key，然后进行一系列的操作而无须“显式”的再次指定Key
 *
 * @author dingli
 * @date 2018年2月1日 上午11:37:08
 */
@Component
public class RedisTemplateManager<PK, T> {
	@Autowired
	private RedisTemplate<PK, T> redisTemplate;
	
	@Value("${redis.expireTime}")
	private Long expire;//Redis key 生命周期，读取配置文件中参数

	/**
	 * get、set操作，提供value压缩机制
	 */
	public Object get(String key,CacheInvocation invocation, Type type){
		byte[] bytes = redisTemplate.execute(new RedisCallback<byte[]>() {

			@Override
			public byte[] doInRedis(RedisConnection redis) throws DataAccessException {
				return redis.get(key.getBytes());
			}
		});
		
		if(bytes == null){
			Object obj = invocation.invoke();
			if(obj != null){
				byte[] objBytes = CompressUtil.compress(JSON.toJSONString(obj));//压缩字符串，减小redis存储空间；值越小，redis存取速度越快。
				redisTemplate.execute(new RedisCallback<String>() {

					@Override
					public String doInRedis(RedisConnection redis) throws DataAccessException {
						byte[] keyBytes = key.getBytes();
						redis.set(keyBytes, objBytes);
						redis.expire(keyBytes, expire);
						return null;
					}
				});
				return obj;
			}
		}else{
			String objStr = CompressUtil.decompress(bytes);
			return JSON.parseObject(objStr, type);
		}
		return null;
	}
	
	/**
	 * push 数组
	 */
	public void addList(PK pk,T t){
		ListOperations<PK, T> operations = redisTemplate.opsForList();
		operations.rightPush(pk, t);
	}
}
