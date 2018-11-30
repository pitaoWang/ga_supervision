package com.atosenet.ga.company.core.auth.shiro.adapter;

import java.util.Map;

import org.apache.shiro.authc.credential.CredentialsMatcher;

public interface AuthProvider extends CredentialsMatcher {
	public static final String TOKEN_SEED = "token_seed";
	
	public static final String USER_AUTH_CACHE = "USER_AUTH_CACHE";
	public static final String TOKEN_SEED_CACHE = "TOKEN_SEED_CACHE";
	
	Object validate(String token);
	
	String genToken(Map<String, Object> info);
	
	String retriToken(String key);
	
	void delete(Object principal);
}
