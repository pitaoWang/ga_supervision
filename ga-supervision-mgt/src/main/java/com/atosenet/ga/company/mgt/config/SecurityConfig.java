package com.atosenet.ga.company.mgt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.atosenet.ga.company.core.auth.shiro.adapter.AuthProvider;
import com.atosenet.ga.company.core.auth.shiro.adapter.local.LocalAuthProvider;

@Configuration
public class SecurityConfig {
	@Bean
	public AuthProvider authProvider() {
		LocalAuthProvider authProvider = new LocalAuthProvider();
		return authProvider;
	}
}
