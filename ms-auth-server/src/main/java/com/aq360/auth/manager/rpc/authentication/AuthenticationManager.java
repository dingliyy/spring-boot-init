/**
 * 
 */
package com.aq360.auth.manager.rpc.authentication;

import org.springframework.beans.factory.annotation.Autowired;

import com.aq360.auth.common.constant.JwtConstants;
import com.aq360.auth.common.domain.bean.JWTInfo;
import com.aq360.auth.common.util.StringHelper;
import com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse;
import com.aq360.auth.manager.rpc.authentication.AuthenticationServiceGrpc.AuthenticationServiceImplBase;
import com.aq360.auth.service.AuthenticationExpireService;
import com.aq360.auth.service.AuthenticationService;

import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

/**
 * 
 *
 * @author dingli02
 * @date 2018/05/18 12:29
 */
@GrpcService(AuthenticationServiceGrpc.class)
public class AuthenticationManager extends AuthenticationServiceImplBase {
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private AuthenticationExpireService authenticationExpireService;

	@Override
    public void validateToken(com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenRequest request,
        io.grpc.stub.StreamObserver<com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse> responseObserver) {
		JWTInfo jwtInfo = authenticationService.parseToken(request.getToken());
		
		String loginId = StringHelper.EMPTY;
		int status = JwtConstants.JWT_ERROR;
		if(jwtInfo == null) {
			status = JwtConstants.JWT_ERROR;
		}else {
			boolean expire = authenticationExpireService.checkExpire(jwtInfo.getUserId());
			if(! expire) {
				status = JwtConstants.JWT_EXPIRE;
			}else {
				authenticationExpireService.refreshExpireTime(jwtInfo.getUserId());
				status = JwtConstants.JWT_SUCCESS;
				loginId = jwtInfo.getUserId();
			}
		}
		ValidateTokenResponse response = ValidateTokenResponse.newBuilder().setStatus(status).setLoginId(loginId).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
    }
	
}
