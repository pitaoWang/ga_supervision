package com.atosenet.ga.company.core.auth.shiro.adapter.ga;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.atosenet.ga.company.core.auth.shiro.adapter.AuthProvider;
import com.atosenet.ga.company.core.auth.shiro.adapter.CredentialsMatcherSupport;
import com.atosenet.ga.company.core.auth.shiro.common.utils.HttpUtils;
import com.google.common.collect.Maps;

public class HttpAuthProvider extends CredentialsMatcherSupport implements AuthProvider {
	public static Logger logger = LoggerFactory.getLogger(HttpAuthProvider.class);

	public static final String USERNAME_KEY = "username";
	public static final String PASSWORD_KEY = "password";

	private String authUrl = "http://127.0.0.1:9090";

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	@Override
	public Object validate(String token) throws AuthenticationException {
		JSONObject retVal = HttpUtils.gets(authUrl + "/" + token, null);
		if (retVal != null) {
			Integer statusCode = retVal.getInteger(HttpUtils.STATUS_CODE_KEY);
			if (statusCode == 200) {
				Integer code = retVal.getInteger("code");
				if (code == 200) {
					String data = retVal.getString("data");
					return data;
				} else {
					String message = retVal.getString("message");
					throw new AuthenticationException(message);
				}
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public String genToken(Map<String, Object> info) throws AuthenticationException {
		String userName = (String) info.get(USERNAME_KEY);
		String password = (String) info.get(PASSWORD_KEY);
		Map<String, String> params = Maps.newHashMap();
		params.put(PASSWORD_KEY, userName);
		params.put(PASSWORD_KEY, password);
		JSONObject retVal = HttpUtils.post(authUrl, params);
		if (retVal != null) {
			Integer statusCode = retVal.getInteger(HttpUtils.STATUS_CODE_KEY);
			if (statusCode == 200) {
				Integer code = retVal.getInteger("code");
				if (code == 200) {
					String token = retVal.getString("data");
					return token;
				} else {
					String message = retVal.getString("message");
					throw new AuthenticationException(message);
				}
			} else {
				return null;
			}
		}
		return null;

	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String tokenStr = (String) getCredentials(token);
		logger.debug("Ga CredentialsMatcher: do validate token[{}]", tokenStr);
		String userName = (String) validate(tokenStr);
		return !StringUtils.isBlank(userName);
	}

	protected Object getCredentials(AuthenticationToken token) {
		return token.getCredentials();
	}

	protected Object getCredentials(AuthenticationInfo info) {
		return info.getCredentials();
	}

	@Override
	public String retriToken(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Object principal) {
		// TODO Auto-generated method stub
		
	}
}
