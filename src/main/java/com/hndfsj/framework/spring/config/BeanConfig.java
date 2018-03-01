package com.hndfsj.framework.spring.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.hndfsj.framework.utils.SpringContextHolder;

import net.sf.ehcache.CacheManager;


@Configuration
@EnableCaching
public class BeanConfig {

	/**
	 * 存储Spring ApplicationContext
	 * @return
	 * zxj
	 */
	@Bean("springContextHolder")
	public SpringContextHolder springContextHolder() {
		return new SpringContextHolder();
	}
	@Bean("springCacheManager")
	public  EhCacheCacheManager EhCacheCacheManager() {
		return new EhCacheCacheManager(cacheManager());
	}
	@Bean("cacheManager")
	public CacheManager cacheManager() {
		EhCacheManagerFactoryBean  ehCacheManagerFactoryBean=new EhCacheManagerFactoryBean();
		ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("classpath:ehcache/ehcache.xml"));
		return ehCacheManagerFactoryBean.getObject();
	}
	
}
