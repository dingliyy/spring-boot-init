/**
 * @author dingli
 * @date 2018年5月31日
 */
package com.aq360.gateway.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest;
import com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse;

/**
 * 
 *
 * @author dingli02
 * @date 2018/05/31 16:04
 */
@Service
public class AuthServerRpc {
	@Autowired
	private AuthServerGrpcConf authServerGrpcConf;
	
	public ValidateTokenResponse validateToken(String token,String url) {
		ValidateTokenRequest request = ValidateTokenRequest.newBuilder().setToken(token).setUrl(url).build();
		ValidateTokenResponse response = authServerGrpcConf.getStub().validateToken(request);
        return response;
	}

}
