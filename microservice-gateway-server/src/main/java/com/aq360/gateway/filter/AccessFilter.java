package com.aq360.gateway.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aq360.auth.manager.rpc.authentication.AuthenticationProto.ValidateTokenResponse;
import com.aq360.gateway.common.constant.JwtConstants;
import com.aq360.gateway.common.domain.bean.ReturnResult;
import com.aq360.gateway.common.util.JsonUtil;
import com.aq360.gateway.rpc.AuthServerRpc;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccessFilter extends ZuulFilter {

	@Value("${request.ignore.startWith}")
	private String ignorePreUris;

	@Value("${request.header.token}")
	private String tokenHeader;
	
	@Value("${request.header.loginId}")
	private String loginId;
	
	@Autowired
	private AuthServerRpc authServerRpc;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String method = request.getMethod();
		String requestUri = request.getRequestURI();

		if (this.isStartWith(requestUri)) {
			return null;
		}

		boolean hasPermissions = false;
		try {
			String token = request.getHeader(tokenHeader);
			ValidateTokenResponse response = this.checkToken(token, requestUri);
			
			if(JwtConstants.JWT_SUCCESS == response.getStatus()) {
				hasPermissions = true;
				ctx.addZuulRequestHeader(this.loginId, response.getLoginId());
			}
			ctx.addZuulRequestHeader(this.tokenHeader, token);
		} catch (Exception e) {
			log.error("token 校验失败", e.getMessage());
		}
		ctx.setSendZuulResponse(hasPermissions);
		// 如果token校验失败，返回错误代码
		if (!hasPermissions) {

			ReturnResult result = new ReturnResult();
			result.setCode(JwtConstants.JWT_ERROR);
			result.setMsg("请重新登录");
			try {
				ctx.setResponseStatusCode(401);
				String responseStr = JsonUtil.obj2Json(result);
				ctx.setResponseBody(responseStr);
				ctx.getResponse().setHeader("Content-type", "text/html;charset=UTF-8");
			} catch (IOException e) {
				log.error("格式化返回信息失败 error message : " + e.getMessage());
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	// 放过不需要校验的请求
	private boolean isStartWith(String requestUri) {
		boolean flag = false;
		for (String s : ignorePreUris.split(",")) {

			String patternStr = s + ".*";
			Pattern pattern = Pattern.compile(patternStr);
			if (pattern.matcher(requestUri).matches()) {
				return true;
			}
		}
		return flag;
	}

	// 远程调用auth-server
	private ValidateTokenResponse checkToken(String token, String url) {
		return this.authServerRpc.validateToken(token, url);
	}
}
