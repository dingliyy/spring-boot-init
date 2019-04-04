/**
 * @author dingli
 * @date 2018年8月21日
 */
package com.tv189.core.util.lock.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.tv189.core.util.lock.Lock;
import com.tv189.core.util.lock.LockCallBack;
import com.tv189.core.util.lock.exception.LockLogicHandleException;
import com.tv189.core.util.lock.exception.LockOccupancyException;
import com.tv189.core.util.lock.exception.LockParamNoneException;

import lombok.extern.slf4j.Slf4j;

/**
 * redis分布式锁
 *
 * @author dingli02
 * @date 2018/08/21 16:25
 */
@Component
@Slf4j
public class RedisLock implements Lock {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    @Override
	public Object singleLockHandle(String key, LockCallBack callBack, long expereTime) {
    	if(key == null) {
			throw new LockParamNoneException("The key of parameters is none!");
		}
    	
		boolean locked = false;
		Object obj = null;
		try {
			locked = tryLock(key, expereTime);
			if (!locked) {
				throw new LockOccupancyException("Lock occupied!");
			}
			obj = callBack.handle();
			releaseLock(key);
		} catch (Exception e) {
			log.error("Lock callBack processing exception!", e);
			if(locked) {
				releaseLock(key);
			}
			throw new LockLogicHandleException(e);
		}
		return obj;
	}

	@Override
	public Object requestLockHandle(String key, LockCallBack callBack, long expereTime) {
		if(key == null) {
			throw new LockParamNoneException("The key of parameters is none!");
		}
		
		boolean locked = false;
		Object obj = null;
		try {
			locked = tryTryLock(key, expereTime);
			if (!locked) {
				throw new LockOccupancyException("Lock occupied!");
			}
			obj = callBack.handle();
			releaseLock(key);
		} catch (Exception e) {
			log.error("Lock callBack processing exception!", e);
			if(locked) {
				releaseLock(key);
			}
			throw new LockLogicHandleException(e);
		}
		return obj;
	}

    private boolean tryLock(String key , long expereTime) {
        Long currentTime = this.currentTime();
        
        // 获取锁
        boolean success = redisTemplate.execute((RedisConnection conn) -> {
    
            boolean succ = conn.setNX(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(currentTime.toString()));
            if(succ) {
            	conn.expire(key.getBytes(), expereTime);// 3分钟
            }
            return succ;
        });
        if(success) return success;
        
        // 防止宕机导致锁一直存在，比较锁value的时间值与当前时间，如果锁是在很久以前加上的，则可以认为该锁异常，可强制释放锁
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String value = operations.get(key);
        if(StringUtils.isNotEmpty(value)) {
        	Long oldTime = Long.valueOf(value);
            
            if(currentTime - oldTime > expereTime * 1000) {// 3分钟
                // 释放锁
                releaseLock(key);
                
                // 再次获取锁
                return redisTemplate.execute((RedisConnection conn) -> {
                    
                    boolean succ = conn.setNX(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(currentTime.toString()));
                    if(succ) {
                    	conn.expire(key.getBytes(), expereTime);// 3分钟
                    }
                    return succ;
                });
            }
        }
        return false;
    }
    
    private boolean tryTryLock(String key, long expereTime) {
        Long currentTime = this.currentTime();
        
        // 获取锁
        boolean success = false;
        for (int i = 0; i < 15; i++) {
	        success = redisTemplate.execute((RedisConnection conn) -> {
	    
                boolean succ = conn.setNX(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(currentTime.toString()));
                if(succ) {
                	conn.expire(key.getBytes(), expereTime);// 3分钟
                }
                return succ;
	        });
	        if(success) return success;
	        
	        try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.error("tryTryLock sleep error ", e);
                Thread.currentThread().interrupt();
            }
        }
        
        // 防止宕机导致锁一直存在，比较锁value的时间值与当前时间，如果锁是在很久以前加上的，则可以认为该锁异常，可强制释放锁
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String value = operations.get(key);
        if(StringUtils.isNotEmpty(value)) {
        	Long oldTime = Long.valueOf(value);
            
            if(currentTime - oldTime > expereTime * 1000) {// 3分钟
                // 释放锁
                releaseLock(key);
                
                // 再次获取锁
                return redisTemplate.execute((RedisConnection conn) -> {
                    
                    boolean succ = conn.setNX(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(currentTime.toString()));
                    if(succ) {
                    	conn.expire(key.getBytes(), expereTime);// 3分钟
                    }
                    return succ;
                });
            }
        }
        return false;
    }

    private void releaseLock(String key) {
        redisTemplate.execute((RedisConnection conn) -> conn.del(key.getBytes()));
    }
    
    private Long currentTime() {
        return redisTemplate.execute(RedisConnection::time);
    }
    
}
