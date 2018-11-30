package com.atosenet.ga.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MvcWebConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/data/api/**").allowedMethods("*");
		registry.addMapping("/app/api/**").allowedMethods("*");
		registry.addMapping("/api/**").allowedMethods("*");
		registry.addMapping("/login").allowedMethods("*");
		registry.addMapping("/profile").allowedMethods("*");
	}

}
