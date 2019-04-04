/**
 * @author dingli
 * @date 2018年9月11日
 */
package com.tv189.core.util.lock.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.tv189.core.util.lock.FieldLock;
import com.tv189.core.util.lock.LockCallBack;
import com.tv189.core.util.lock.exception.LockLogicHandleException;
import com.tv189.core.util.lock.exception.LockOccupancyException;
import com.tv189.core.util.lock.exception.LockParamNoneException;

import lombok.extern.slf4j.Slf4j;

/**
 * redis锁
 *
 * @author dingli02
 * @date 2018/09/11 10:43
 */
@Component
@Slf4j
public class RedisFieldLock implements FieldLock {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    @Override
	public Object requestLockHandle(String key, String field, LockCallBack callBack, long expereTime) {
    	if(key == null) {
			throw new LockParamNoneException("The key of parameters is none!");
		}
    	
		boolean locked = false;
		Object obj = null;
		try {
			locked = tryTryLock(key, field, expereTime);
			if (!locked) {
				throw new LockOccupancyException("Lock occupied!");
			}
			obj = callBack.handle();
			releaseLock(key, field);
		} catch (Exception e) {
			log.error("Lock callBack processing exception!", e);
			if(locked) {
				releaseLock(key, field);
			}
			throw new LockLogicHandleException(e);
		}
		return obj;
	}

	@Override
	public Object singleLockHandle(String key, String field, LockCallBack callBack, long expereTime) {
		if(key == null) {
			throw new LockParamNoneException("The key of parameters is none!");
		}
		
		boolean locked = false;
		Object obj = null;
		try {
			locked = tryLock(key, field, expereTime);
			if (!locked) {
				throw new LockOccupancyException("Lock occupied!");
			}
			obj = callBack.handle();
			releaseLock(key, field);
		} catch (Exception e) {
			log.error("Lock callBack processing exception!", e);
			if(locked) {
				releaseLock(key, field);
			}
			throw new LockLogicHandleException(e);
		}
		return obj;
	}

    
    private boolean tryLock(String key, String field, long expereTime) {
        Long currentTime = this.currentTime();
        
        // 获取锁
        boolean success = redisTemplate.execute((RedisConnection conn) -> {
    
            boolean succ = conn.hSetNX(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(field), redisTemplate.getStringSerializer().serialize(currentTime.toString()));
            if(succ) {
            	conn.expire(key.getBytes(), expereTime);
            }
            return succ;
        });
        if(success) return success;
        
        // 防止宕机导致锁一直存在，比较锁value的时间值与当前时间，如果锁是在很久以前加上的，则可以认为该锁异常，可强制释放锁
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String value = operations.get(key);
        if(StringUtils.isNotEmpty(value)) {
            Long oldTime = Long.valueOf(value);
            
            if(currentTime - oldTime > expereTime * 1000) {
                // 释放锁
                releaseLock(key ,field);
                
                // 再次获取锁
                return redisTemplate.execute((RedisConnection conn) -> {
                    
                    boolean succ = conn.hSetNX(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(field), redisTemplate.getStringSerializer().serialize(currentTime.toString()));
                    if(succ) {
                    	conn.expire(key.getBytes(), expereTime);// 3分钟
                    }
                    return succ;
                });
            }
        }
        return false;
    }

    private boolean tryTryLock(String key, String field, long expereTime) {
        Long currentTime = this.currentTime();

        // 获取锁
        boolean success = false;
        for (int i = 0; i < 15; i++) {
            success = redisTemplate.execute((RedisConnection conn) -> {

                boolean succ = conn.hSetNX(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(field), redisTemplate.getStringSerializer().serialize(currentTime.toString()));
                if(succ) {
                	conn.expire(key.getBytes(), expereTime);
                }
                return succ;
            });
            if (success)
                return success;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.error("tryTryLock sleep error ", e);
                Thread.currentThread().interrupt();
            }
        }

        // 防止宕机导致锁一直存在，比较锁value的时间值与当前时间，如果锁是在很久以前加上的，则可以认为该锁异常，可强制释放锁
        HashOperations<String, Object, Object> operations = redisTemplate.opsForHash();
        Object value = operations.get(key, field);
        if(value != null) {
            Long oldTime = Long.valueOf(value.toString());

            if (currentTime - oldTime > expereTime * 1000) {// 3分钟
                // 释放锁
                releaseLock(key, field);

                // 再次获取锁
                return redisTemplate.execute((RedisConnection conn) -> {

                    boolean succ = conn.hSetNX(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(field), redisTemplate.getStringSerializer().serialize(currentTime.toString()));
                    if(succ) {
                    	conn.expire(key.getBytes(), expereTime);// 3分钟
                    }
                    return succ;
                });
            }
        }
        return false;
    }

    private void releaseLock(String key, String field) {
        redisTemplate.execute((RedisConnection conn) -> conn.del(key.getBytes(), field.getBytes()));
    }

    private Long currentTime() {
        return redisTemplate.execute(RedisConnection::time);
    }
}
