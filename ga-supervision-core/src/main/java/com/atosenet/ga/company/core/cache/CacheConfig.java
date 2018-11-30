package com.atosenet.ga.company.core.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CacheConfig {
	@Bean
    public CacheManager cacheManager() {
        EhCacheCacheManager manager = new EhCacheCacheManager();
        manager.setCacheManager(cacheManagerFactoryBean().getObject());
        return manager;
    }
	
	@Bean
	public EhCacheManagerFactoryBean cacheManagerFactoryBean() {
		EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("config/cache/ehcache.xml"));
		return cacheManagerFactoryBean;
	}
}
