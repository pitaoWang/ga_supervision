package com.atosenet.ga.company.core.auth.shiro.adapter.local;

import java.util.Date;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import com.atosenet.ga.company.core.auth.shiro.adapter.AuthProvider;
import com.atosenet.ga.company.core.auth.shiro.adapter.CredentialsMatcherSupport;
import com.atosenet.ga.company.core.auth.shiro.common.esapi.EncryptException;
import com.atosenet.ga.company.core.auth.shiro.token.TokenGenerator;

public class LocalAuthProvider extends CredentialsMatcherSupport implements AuthProvider {

	public static Logger logger = LoggerFactory.getLogger(LocalAuthProvider.class);
	
	public static final String USERNAME_KEY = "username";
	public static final String PASSWORD_KEY = "password";
	
	@Autowired
	private CacheManager cacheManager;
	
//	@Autowired
//	private UserRepository userRepository;
	
	@Override
	public Object validate(String token) {
		return null;
	}
	
	@Override
	public String retriToken(String key) {
		String token = cacheManager.getCache(USER_AUTH_CACHE).get(key, String.class);
		return token;
	}

	@Override
	public String genToken(Map<String, Object> info) {
		String userName = (String) info.get(USERNAME_KEY);
		try {
//			User user = userRepository.findByAccount(userName);
//			if(user == null) {
//				return null;
//			}
			String seed = TokenGenerator.findSeed();
			String tokenStr = TokenGenerator.genToken(userName, (new Date()).getTime(), seed);
			logger.debug("Caching user auth info: username={}, token={}", userName, tokenStr);
			cacheManager.getCache(USER_AUTH_CACHE).put(userName, tokenStr);
			
			return tokenStr;
		} catch (EncryptException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		return true;
	}

	@Override
	public void delete(Object principal) {
		String cuser = (String) principal;
		cacheManager.getCache(USER_AUTH_CACHE).evict(cuser);
	}
}
