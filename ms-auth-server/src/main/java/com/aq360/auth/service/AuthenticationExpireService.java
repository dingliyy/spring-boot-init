/**
 * @author dingli
 * @date 2018年5月31日
 */
package com.aq360.auth.service;

/**
 * 认证过期时间校验
 *
 * @author dingli02
 * @date 2018/05/31 19:01
 */
public interface AuthenticationExpireService {
	
	/**
	 * 校验token是否过期
	 * @param token
	 * @return true 没有过期，false 过期
	 */
	boolean checkExpire(String token);
	
	/**
	 * 刷新token过期时间
	 * @param token
	 */
	void refreshExpireTime(String token);
}
