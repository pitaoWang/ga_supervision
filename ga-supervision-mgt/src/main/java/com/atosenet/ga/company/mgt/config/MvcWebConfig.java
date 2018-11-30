package com.atosenet.ga.company.mgt.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.atosenet.ga.company.mgt.interceptor.WebCounterInterceptor;

@Configuration
public class MvcWebConfig extends WebMvcConfigurerAdapter {
	@Bean
	WebCounterInterceptor webCounterInterceptor() {
		return new WebCounterInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(webCounterInterceptor());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/data/api/**").allowedMethods("*");
		registry.addMapping("/app/api/**").allowedMethods("*");
		registry.addMapping("/api/**").allowedMethods("*");
		registry.addMapping("/login").allowedMethods("*");
		registry.addMapping("/profile").allowedMethods("POST");
		registry.addMapping("/auth_code").allowedMethods("POST");
		registry.addMapping("/validate_token").allowedMethods("POST");
		
	}

	@Bean
	CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(209715200);
		multipartResolver.setResolveLazily(true);
		return multipartResolver;
	}

	//by dgw
	 @Bean  
	    public FilterRegistrationBean corsFilter() {  
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
	        CorsConfiguration config = new CorsConfiguration();  
	        config.setAllowCredentials(true);  
	        config.addAllowedOrigin("*");
	        config.addAllowedHeader(CorsConfiguration.ALL);  
	        config.addAllowedMethod(CorsConfiguration.ALL);  
	        source.registerCorsConfiguration("/**", config);  
	        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));  
	        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);  
	        return bean;  
	    }  

}
