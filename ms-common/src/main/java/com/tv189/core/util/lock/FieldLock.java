/**
 * @author dingli
 * @date 2018年9月11日
 */
package com.tv189.core.util.lock;

/**
 * 分布式锁
 *
 * @author dingli02
 * @date 2018/09/11 10:40
 */
public interface FieldLock {
    
	/**
	 * 	请求锁处理
	 *	
	 * @param key 锁一级key
	 * @param field 锁二级key
	 * @param callBack 业务处理回调函数
	 * @param expereTime 失效时间，单位秒
	 * @return
	 */
	Object requestLockHandle(String key, String field, LockCallBack callBack, long expereTime);
	
	/**
	 *	 单次锁处理
	 *	
	 * @param key 锁一级key
	 * @param field 锁二级key
	 * @param callBack 业务处理回调函数
	 * @param expereTime 失效时间，单位秒
	 * @return
	 */
	Object singleLockHandle(String key, String field, LockCallBack callBack, long expereTime);
}
