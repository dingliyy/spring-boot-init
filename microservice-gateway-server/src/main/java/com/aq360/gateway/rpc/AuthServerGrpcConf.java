/**
 * @author dingli
 * @date 2018年5月31日
 */
package com.aq360.gateway.rpc;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.aq360.auth.manager.rpc.authentication.AuthenticationServiceGrpc;
import com.aq360.auth.manager.rpc.authentication.AuthenticationServiceGrpc.AuthenticationServiceBlockingStub;

import io.grpc.Channel;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;

/**
 * 
 *
 * @author dingli02
 * @date 2018/05/31 18:29
 */
@Component
public class AuthServerGrpcConf {
	@GrpcClient("auth-server")
	private Channel serverChannel;
	
	private AuthenticationServiceBlockingStub stub;
	
	public AuthenticationServiceBlockingStub getStub() {
		if(stub == null) {
			stub = AuthenticationServiceGrpc.newBlockingStub(serverChannel);
		}
		return stub;
	}
}
