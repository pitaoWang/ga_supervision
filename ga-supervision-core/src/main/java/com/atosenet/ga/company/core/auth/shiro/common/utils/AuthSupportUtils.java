package com.atosenet.ga.company.core.auth.shiro.common.utils;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.atosenet.ga.company.core.auth.shiro.common.response.AuthResult;

public class AuthSupportUtils {
	
	public static void onAjaxAuth(ServletRequest req, ServletResponse resp, int code, String msg) throws IOException {
		HttpServletResponse response = (HttpServletResponse) resp;
		AuthResult authResult = new AuthResult();
		authResult.setResultCode(code);
		authResult.setMessage(msg);
		response.setStatus(HttpServletResponse.SC_OK);
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, DELETE, GET, OPTIONS");
		response.setHeader("Access-Control-Request-Method", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		response.setHeader("Access-Control-Max-Age", "1800");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(JSONObject.toJSONString(authResult));
	}
	
	public static void onAjaxAuth(ServletRequest req, ServletResponse resp, String msg) throws IOException {
		onAjaxAuth(req, resp, HttpServletResponse.SC_UNAUTHORIZED, msg);
	}

	public static boolean isAjax(ServletRequest request) {
		boolean isAjax = false;
		if (request instanceof HttpServletRequest) {
			HttpServletRequest rq = (HttpServletRequest) request;
			String requestType = rq.getHeader("X-Requested-With");
			if (requestType != null && "XMLHttpRequest".equals(requestType)) {
				isAjax = true;
			}
		}
		return isAjax;
	}
}
