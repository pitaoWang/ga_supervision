package com.atosenet.ga.company.core.cache.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import net.sf.ehcache.event.CacheManagerEventListener;

public class AppCacheManagerEventListener implements CacheManagerEventListener {

	private final Logger logger = LoggerFactory.getLogger(AppCacheManagerEventListener.class);

	private CacheManager cacheManager;
	
	public AppCacheManagerEventListener(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
    @Override
    public void init() throws CacheException {
        logger.info("init ehcache...");
    }

    @Override
    public Status getStatus() {
        return null;
    }

    @Override
    public void dispose() throws CacheException {
        logger.info("ehcache Dispose...");
    }

    @Override
    public void notifyCacheAdded(String s) {
        logger.info("ehcache Added... {}", s);
        logger.info(cacheManager.getCache(s).toString());
    }

    @Override
    public void notifyCacheRemoved(String s) {
    	logger.info("ehcache Removed... {}", s);
    }

}
