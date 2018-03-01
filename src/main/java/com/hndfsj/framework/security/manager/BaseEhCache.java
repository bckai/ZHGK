package com.hndfsj.framework.security.manager;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public abstract class BaseEhCache<T> implements InitializingBean{
	private static Log log = LogFactory.getLog(BaseEhCache.class);
	/**
	 *  EhCacheFactoryBean创建
	 */
	protected Cache cache;

	/**
	 * 从缓存移除资源ResourcDetail对象
	 * @param key_resourceName resource的code
	 */
	public void removeResourcFromCache(String key_resourceName) {
		cache.remove(key_resourceName);
	}

	/**
	 * @return the cache
	 */
	public Cache getCache() {
		return cache;
	}

	/**
	 * @param cache the cache to set
	 */
	public void setCache(Cache cache) {
		this.cache = cache;
	}

	@SuppressWarnings("unchecked")
	public T getResourcFromCache(String key_resourceName) {
		Element element = cache.get(key_resourceName);
		log.debug("============查询缓存<安全资源> :"+key_resourceName+"============");
		if (element == null) {
			return null;
		} else {
			return (T) element.getObjectValue();
		}
	}

	public abstract void putResourcInCache(T detail);
}
