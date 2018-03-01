package com.hndfsj.framework.security.manager;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import com.hndfsj.framework.objects.TreeObject;

import net.sf.ehcache.Element;


/**
 * 封装Cache 
 * 在配置文件的cache key 为  resourceCache) <br>
 * 来对 RWUserDetails 进行缓存
 *
 * @author 王富强
 * @date 2009-5-16 上午10:38:22
 */
public class EhCacheDeptInfoCache extends BaseEhCache<Map<String,TreeObject>> {
	public static String DEPT_TREE="DEPT_TREE";
	private static Log log = LogFactory.getLog(EhCacheDeptInfoCache.class);

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cache, "Cache对象userInfo(Ehcache)不能为空");
	}

	@Override
	public void putResourcInCache(Map<String,TreeObject> dept) {
		Element element = new Element(DEPT_TREE,dept);
		log.debug("@@@@@@@@@@@@缓存数据<部门树>:"+DEPT_TREE+"@@@@@@@@@@@@");
		cache.put(element);
	}
	
	
}
