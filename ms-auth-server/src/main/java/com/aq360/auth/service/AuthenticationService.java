package com.aq360.auth.service;

import com.aq360.auth.common.domain.bean.JWTInfo;

/**
 * 认证接口
 * 
 * @author dingli02
 * @date 2018/05/17 17:45
 */
public interface AuthenticationService {
	
	/**
	 * 解析token
	 * @param token
	 * @return 
	 */
	JWTInfo parseToken(String token);
	
	
	/**
	 * 生产token
	 * @param jwtInfo JWT对象
	 * @return
	 */
	String generateToken(JWTInfo jwtInfo);
	
}
