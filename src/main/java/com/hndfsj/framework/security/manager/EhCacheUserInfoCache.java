package com.hndfsj.framework.security.manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import com.hndfsj.framework.security.bean.RWUserDetails;

import net.sf.ehcache.Element;

/**
 * 封装Cache 
 * 在配置文件的cache key 为  resourceCache) <br>
 * 来对 RWUserDetails 进行缓存
 *
 * @author 王富强
 * @date 2009-5-16 上午10:38:22
 */
public class EhCacheUserInfoCache extends BaseEhCache<RWUserDetails> {
	
	private static Log log = LogFactory.getLog(EhCacheUserInfoCache.class);

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cache, "Cache对象userInfo(Ehcache)不能为空");
	}

	@Override
	public void putResourcInCache(RWUserDetails detail) {
		Element element = new Element(detail.getUsername(),detail);
		log.debug("@@@@@@@@@@@@缓存数据<安全资源>:"+detail.getUsername()+"@@@@@@@@@@@@");
		cache.put(element);
		
	}
	
	
}
