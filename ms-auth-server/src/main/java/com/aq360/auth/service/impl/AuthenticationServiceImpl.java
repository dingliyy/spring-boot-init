package com.aq360.auth.service.impl;

import org.springframework.stereotype.Service;

import com.aq360.auth.common.util.JWTHelper;
import com.aq360.auth.domain.bean.JWTInfo;
import com.aq360.auth.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	/* (non-Javadoc)
	 * @see com.aq360.auth.service.AuthenticationService#generateToken(com.aq360.auth.domain.bean.JWTInfo)
	 */
	@Override
	public String generateToken(JWTInfo jwtInfo) {
		return JWTHelper.generateToken(jwtInfo);
	}

	/* (non-Javadoc)
	 * @see com.aq360.auth.service.AuthenticationService#parseToken(java.lang.String)
	 */
	@Override
	public JWTInfo parseToken(String token) {
		JWTInfo jwtInfo = JWTHelper.getInfoFromToken(token);
		return jwtInfo;
	}

}
