/**
 * @author dingli
 * @date 2018年8月21日
 */
package com.tv189.core.util.lock;

/**
 * 分布式锁
 *
 * @author dingli02
 * @date 2018/08/21 16:20
 */
public interface Lock {
    
	/**
	 * 	单次锁处理
	 *	
	 * @param key 锁key
	 * @param callBack 业务处理
	 * @param expereTime 超时时长（秒）
	 */
    Object singleLockHandle(String key, LockCallBack callBack, long expereTime);
    
    /**
     * 	请求锁处理
     *	
     * @param key 锁key
     * @param callBack 业务处理
     * @param expereTime 超时时长（秒）
     * @return
     */
    Object requestLockHandle(String key, LockCallBack callBack , long expereTime);
    
    
}
