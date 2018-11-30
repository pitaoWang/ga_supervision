package com.atosenet.ga.company.core.cache.listener;

import java.util.Properties;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.event.CacheManagerEventListener;
import net.sf.ehcache.event.CacheManagerEventListenerFactory;

public class AppCacheManagerEventListenerFactory extends CacheManagerEventListenerFactory {
	@Override
	public CacheManagerEventListener createCacheManagerEventListener(CacheManager cacheManager, Properties properties) {
		return new AppCacheManagerEventListener(cacheManager);
	}
}