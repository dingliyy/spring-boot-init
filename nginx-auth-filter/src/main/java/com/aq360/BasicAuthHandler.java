/**
 * @author dingli
 * @date 2018年5月17日
 */
package com.aq360;

import static nginx.clojure.java.Constants.PHASE_DONE;

import java.io.IOException;
import java.util.Map;

import com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse;
import com.aq360.constant.JwtConstants;
import com.aq360.rpc.AuthenticationGrpcClient;

import nginx.clojure.MiniConstants;
import nginx.clojure.java.ArrayMap;
import nginx.clojure.java.NginxJavaRequest;
import nginx.clojure.java.NginxJavaRingHandler;

/**
 * token权限校验
 * 在请求之前拦截
 *
 * @author dingli02
 * @date 2018/05/17 15:43
 */
public class BasicAuthHandler implements NginxJavaRingHandler {

	@Override
	public Object[] invoke(Map<String, Object> request) throws IOException {
		// 这里对token仅做存在性校验，还可以做其他校验，例如调用oauth service接口做认证和授权校验
		String token = (String) ((Map) request.get(MiniConstants.HEADERS)).get("token");
		if (token == null) {
			return new Object[] { 200, ArrayMap.create("Content-Type", "application/json;charset=UTF-8"),
					"{\"code\":" + JwtConstants.JWT_NONE + ",\"msg\":\"No access to resources\"}" };
		} else {
			String url = (String) request.get(MiniConstants.URI);
			
			ValidateTokenResponse jwtResponse = AuthenticationGrpcClient.grpcValidate(token, url);

			if (JwtConstants.JWT_SUCCESS == jwtResponse.getStatus()) {
				((NginxJavaRequest)request).setVariable("loginId", jwtResponse.getLoginId());
				((NginxJavaRequest)request).setVariable("refreshToken", jwtResponse.getRefreshToken());
				return PHASE_DONE;
			} else {
				return new Object[] { 200, ArrayMap.create("Content-Type", "application/json;charset=UTF-8"),
						"{\"code\":" + JwtConstants.JWT_ERROR + ",\"msg\":\"No access to resources\"}" };
			}
		}
	}

}
