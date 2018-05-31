/**
 * @author dingli
 * @date 2018年5月31日
 */
package com.aq360.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aq360.auth.manager.redis.RedisTemplateManager;
import com.aq360.auth.service.AuthenticationExpireService;

/**
 * 
 *
 * @author dingli02
 * @date 2018/05/31 19:03
 */
@Service
public class AuthenticationExpireServiceImpl implements AuthenticationExpireService{
	@Autowired
	private RedisTemplateManager<String, String> redisTemplateManager;
	/* (non-Javadoc)
	 * @see com.aq360.auth.service.AuthenticationExpireService#checkExpire(java.lang.String)
	 */
	@Override
	public boolean checkExpire(String token) {
		return redisTemplateManager.existKey(token);
	}

	/* (non-Javadoc)
	 * @see com.aq360.auth.service.AuthenticationExpireService#refreshExpireTime(java.lang.String)
	 */
	@Override
	public void refreshExpireTime(String token) {
		redisTemplateManager.set(token, token);
	}

}
