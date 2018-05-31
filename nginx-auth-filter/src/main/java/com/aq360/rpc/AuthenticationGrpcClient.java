/**
 * @author dingli
 * @date 2018年5月18日
 */
package com.aq360.rpc;

import com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest;
import com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse;
import com.aq360.auth.manager.rpc.authentication.AuthenticationServiceGrpc;
import com.aq360.auth.manager.rpc.authentication.AuthenticationServiceGrpc.AuthenticationServiceBlockingStub;
import com.aq360.config.ConfigInit;

import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;

/**
 * 权限认证grpc调用
 *
 * @author dingli02
 * @date 2018/05/18 14:34
 */
public class AuthenticationGrpcClient {
	private static final ManagedChannel channel = NettyChannelBuilder.forAddress(ConfigInit.getProperty("ip"), Integer.valueOf(ConfigInit.getProperty("port"))).usePlaintext(true).build(); 
	
	//同步调用(异步调用用SendMailServiceGrpc.newFutureStub(channel))  
	private static final AuthenticationServiceBlockingStub stub = AuthenticationServiceGrpc.newBlockingStub(channel);
	
	/**
	 * 认证校验
	 * @param token
	 * @param url 请求资源url
	 * @return <ul>JWTResponse 对象属性</ul> 
	 * <li> status ： 5000 表示成功，5001 表示失败，5002 表示时间过期；</li> 
	 * <li>refreshToken 是校验成功后刷新的token；</li>
	 * <li>loginId 是当前登录者用户编号；</li>
	 */
	public static ValidateTokenResponse grpcValidate(String token,String url) {  
		
        //设置请求参数  
		ValidateTokenRequest request = ValidateTokenRequest.newBuilder().setToken(token).setUrl(url).build();
		ValidateTokenResponse response = stub.validateToken(request);
        return response;
    }
	
}
