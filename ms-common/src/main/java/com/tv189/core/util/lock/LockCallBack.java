/**
 *	
 * @author dingli02  
 * @date 2018/10/11 11:17
 */
package com.tv189.core.util.lock;

/**
 *	锁回调函数
 * @author dingli02  
 * @date 2018/10/11 11:17
 */
public interface LockCallBack {
	/**
	 * 	业务处理
	 *	
	 */
	Object handle();
}
