/**
 * @author dingli
 * @date 2018年5月18日
 */
package com.aq360;

import static nginx.clojure.java.Constants.PHASE_DONE;

import java.io.IOException;
import java.util.Map;

import nginx.clojure.MiniConstants;
import nginx.clojure.java.NginxJavaHeaderFilter;
import nginx.clojure.java.NginxJavaRequest;

/**
 * 添加response header
 * 在请求之后，返回之前拦截
 *
 * @author dingli02
 * @date 2018/05/18 14:32
 */
public class BasicAuthHeaderFilter implements NginxJavaHeaderFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see nginx.clojure.java.NginxJavaHeaderFilter#doFilter(int, java.util.Map,
	 * java.util.Map)
	 */
	@Override
	public Object[] doFilter(int status, Map<String, Object> request, Map<String, Object> responseHeaders)
			throws IOException {
		String refreshToken = ((NginxJavaRequest) request).getVariable("refreshToken");
		responseHeaders.put("refreshToken", refreshToken);
		return PHASE_DONE;
	}

}
