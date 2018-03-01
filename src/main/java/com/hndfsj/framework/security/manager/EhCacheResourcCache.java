/**
 *
 */
package com.hndfsj.framework.security.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.hndfsj.framework.security.bean.RWResourceDetail;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;


/**
 * 封装Cache(这个Cache是由srping的org.springframework.cache.ehcache.EhCacheFactoryBean来创建的 <br>
 * 在配置文件的cache key 为  resourceCache) <br>
 * 来对 RWResourcDetail 进行缓存
 *
 * @author 王富强
 * @date 2009-5-16 上午10:38:22
 */
public class EhCacheResourcCache implements InitializingBean {

	private static Log log = LogFactory.getLog(EhCacheResourcCache.class);
	/**
	 * 
	 */
	public static final String URL_RESOURCES = "URL";
	/**
	 * 
	 */
	public static final String METHOD_RESOURCES = "METHOD";

	/**
	 *  EhCacheFactoryBean创建
	 */
	private Cache cache;

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		//org.springframework.cache.ehcache.EhCacheFactoryBean创建的 resourceCache不能为null
		Assert.notNull(cache, "Cache对象(Ehcache)不能为空");
	}

	/**
	 * 从缓存中获取资源ResourcDetail对象	 
	 * 
	 * @param key_resourceName  resource的code
	 * @return
	 */
	public RWResourceDetail getResourcFromCache(String key_resourceName) {
		Element element = cache.get(key_resourceName);
//		log.debug("============查询缓存<安全资源> :"+key_resourceName+"============");
		if (element == null) {
			return null;
		} else {
			return (RWResourceDetail) element.getValue();
		}
	}
	/**
	 * 缓存资源
	 * @param detail
	 */
	public void putResourcInCache(RWResourceDetail detail) {
		Element element = new Element(detail.getResourceName()+detail.getResourceRESTMethod(),detail);
		log.debug("@@@@@@@@@@@@缓存数据<安全资源>:"+detail.getResourceName()+detail.getResourceRESTMethod()+"@@@@@@@@@@@@");
		cache.put(element);
	}
	/**
	 * 从缓存移除资源ResourcDetail对象
	 * @param key_resourceName resource的code
	 */
	public void removeResourcFromCache(String key_resourceName) {
		cache.remove(key_resourceName);
	}

	/**
	 * 获取URL类型的安全资源
	 * @return
	 */
	public List<String> getUrlResources() {
		return getResourcesByType(URL_RESOURCES);
	}
	/**
	 * 获取方法类型的安全资源
	 * @return
	 * @deprecated
	 */
	public List<String> getMethodResources() {
		return getResourcesByType(METHOD_RESOURCES);
	}

	/**
	 * 通过资源的类型来获取相应的资源(暂时只用URL_RESOURCES)
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<String> getResourcesByType(String type) {
		List<String> resources = new ArrayList<String>();
		List<String> keysList = this.cache.getKeys();
		for (String key_resourceName : keysList) {
			RWResourceDetail detail = getResourcFromCache(key_resourceName);
			if (detail != null && detail.getResourceType().equals(type)) {
				resources.add(detail.getResourceUrl());
			}
		}
		return resources;
	}

	public List<RWResourceDetail> getAllUrlResourcDetail(){
		return getAllByType(URL_RESOURCES);
	}
	@SuppressWarnings("unchecked")
	private List<RWResourceDetail> getAllByType(String type) {
		List<RWResourceDetail> details = new ArrayList<RWResourceDetail>();
		List<String> keysList = this.cache.getKeys();
		for (String key_resourceName : keysList) {
			RWResourceDetail detail = getResourcFromCache(key_resourceName);
			if (detail != null) {
				details.add(detail);
			}else{
				System.err.println(cache.getSize());
			}
		}
		return details;
	}
	@SuppressWarnings({ "unchecked", "unused" })
	private List<RWResourceDetail> getAll() {
		List<RWResourceDetail> details = new ArrayList<RWResourceDetail>();
		List<String> keysList = this.cache.getKeys();
		for (String key_resourceName : keysList) {
			RWResourceDetail detail = getResourcFromCache(key_resourceName);
				details.add(detail);
		}
		return details;
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

}
