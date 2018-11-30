package com.atosenet.ga.company.core.auth.shiro.stateless;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atosenet.ga.company.core.auth.shiro.adapter.AuthProvider;
import com.atosenet.ga.company.core.auth.shiro.common.utils.AuthSupportUtils;
import com.atosenet.ga.company.core.auth.shiro.common.utils.CookieUtil;
import com.atosenet.ga.company.core.auth.shiro.common.utils.InvocationInfoProxy;

public class StatelessAuthcFilter extends AccessControlFilter {

	private static final Logger logger = LoggerFactory.getLogger(StatelessAuthcFilter.class);

	public static final int HTTP_STATUS_AUTH = 306;

	public static final String PARAM_USERNAME = "un";
	public static final String PARAM_LOGINTS = "ts";
	public static final String PARAM_TOKEN = "tk";

	private AuthProvider authProvider;

	public AuthProvider getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(AuthProvider authProvider) {
		this.authProvider = authProvider;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		HttpServletRequest httprequest = (HttpServletRequest) request;
		if (httprequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
			return true;
		} else
			return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

		if (!AuthSupportUtils.isAjax(request)) {
			// throw new AuthenticationException("invalid request, support rest
			// only");
			AuthSupportUtils.onAjaxAuth(request, response, "invalid request, support rest only");
			return false;
		}

		// 1、客户端发送来的摘要
		HttpServletRequest hReq = (HttpServletRequest) request;
		HttpServletRequest httpRequest = hReq;
		Cookie[] cookies = httpRequest.getCookies();
		String authority = httpRequest.getHeader("Authority");

		// 如果header中包含，则以header为主，否则，以cookie为主
		if (StringUtils.isNotBlank(authority)) {
			Set<Cookie> cookieSet = new HashSet<Cookie>();
			String[] ac = authority.split(";");
			for (String s : ac) {
				String[] cookieArr = s.split("=");
				if (cookieArr.length != 2) {
					AuthSupportUtils.onAjaxAuth(request, response, "invalid authority");
					return false;
				}
				String key = StringUtils.trim(cookieArr[0]);
				String value = StringUtils.trim(cookieArr[1]);
				Cookie cookie = new Cookie(key, value);
				cookieSet.add(cookie);
			}
			cookies = cookieSet.toArray(new Cookie[] {});
		}

		String tokenStr = CookieUtil.findCookieValue(cookies, PARAM_TOKEN);
		String cookieUserName = CookieUtil.findCookieValue(cookies, PARAM_USERNAME);
		String loginTs = CookieUtil.findCookieValue(cookies, PARAM_LOGINTS);

		// 2、客户端传入的用户身份
		String username = request.getParameter(PARAM_USERNAME);
		if (username == null && StringUtils.isNotBlank(cookieUserName)) {
			username = cookieUserName;
		}

		if (StringUtils.isBlank(tokenStr)) {
			// throw new AuthenticationException("require token");
			AuthSupportUtils.onAjaxAuth(httpRequest, response, "require token");
			return false;
		}
		String clientDigest = new String(tokenStr);

		logger.debug("onAccessDenied, username={}, loginTs={}, clientDigest={}", username, loginTs, clientDigest);

		// 3、客户端请求的参数列表
		Map<String, String[]> params = new HashMap<String, String[]>(request.getParameterMap());

		// 4、生成无状态Token
		StatelessAuthenticationToken token = new StatelessAuthenticationToken(username, params, clientDigest);

		try {
			// 5、委托给Realm进行登录
			getSubject(request, response).login(token);
			// 设置上下文变量
			InvocationInfoProxy.setUserName(username);
			InvocationInfoProxy.setLoginTs(loginTs);
			InvocationInfoProxy.setToken(tokenStr);
		} catch (Exception e) {
			logger.info("Auth faild：{}", e.getMessage());
			// throw new AuthenticationException(e.getMessage());
			// 6、登录失败
			AuthSupportUtils.onAjaxAuth(request, response, e.getMessage());
			return false;// 就直接返回给请求者.
		}
		return true;

	}
}