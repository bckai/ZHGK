package com.hndfsj.framework.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hndfsj.admin.domain.Dictionary;
import com.hndfsj.admin.domain.Role;
import com.hndfsj.app.common.enums.ZHGKEnum.DICTIONARY_COUNT;
import com.hndfsj.app.common.enums.ZHGKEnum.DICTIONARY_TYPE;
import com.hndfsj.app.common.enums.ZHGKEnum.ZHGKEnum_Role;
import com.hndfsj.driver.service.HDServiceManager;
import com.hndfsj.framework.exceptions.InitException;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;
import com.hndfsj.framework.utils.SpringContextHolder;
import com.hndfsj.framework.utils.driver.ConstantPool;

/**
 * 初始化程序运程需要的数据
 * 
 * @copyright {@link www.hndfsjsoft.com}
 * @author
 * @version 2015年3月27日 下午2:09:38
 * @see com.hndfsj.framework.listener.InitResouceListener
 */
public class InitResouceListener implements ServletContextListener {

	private static Logger log = LoggerFactory.getLogger(InitResouceListener.class);

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		log.info("[初始化应用" + servletContextEvent.getServletContext().getServletContextName() + "运行需要的数据]");
		init();
		checkSystemConfig();
	}

	/**
	 * 程序启动检查系统配置
	 * 
	 * @author Mr.Zheng
	 * @version 2015年3月27日 下午2:35:00
	 */
	private void checkSystemConfig() {
		// 程序启动需要检查的配置
		checkDataStoreConfig();
	}

	private void checkDataStoreConfig() {
		com.hndfsj.admin.service.IRoleService roleService = SpringContextHolder.getBean("roleService");
		// 数据库配置检查
		// 检查sys_role中是否有基础角色
		for (ZHGKEnum_Role enum_Role : ZHGKEnum_Role.values()) {
			boolean isExist = false;
			if (roleService.isExist("id", enum_Role.getId())) {
				isExist = true;
			}
			if (!isExist) {
				try {
					Role role = new Role(enum_Role.getId(), enum_Role.name(), enum_Role.name());
					role.setIsValid(true);
					roleService.save(role);

				} catch (Exception e) {
					log.error("", e);
					throw new InitException(enum_Role.name() + "角色添加失败,请检查数据库配置，或确认sys_role表是否存在");
				}
			}
		}
		// 硬件初始化
		Thread hdService = new Thread(new HDServiceManager());
		ConstantPool.THREAD_POOL.add(hdService); // 注册入线程池 hdService.start();
		hdService.start();
		 
		log.info("===========硬件初始化完成============");
	}

	/**
	 * 统计初始化
	 */
	private void init() {
		com.hndfsj.admin.service.IDictionaryService dictionaryService = SpringContextHolder
				.getBean("dictionaryService");
		// 检查字典表里的统计，添加统计字段
		for (DICTIONARY_COUNT dict : DICTIONARY_COUNT.values()) {
			PageRequest pageRequest = new PageRequest();
			pageRequest.addAndCondition(Dictionary.DICTYPE, SearchCondition.EQUAL, dict.getId());
			pageRequest.addAndCondition(Dictionary.CODE, SearchCondition.EQUAL, dict.name());
			if (dictionaryService.getCount(pageRequest) == 0) {
				dictionaryService.save(new Dictionary(dict.name(), "0", dict.getId().toString()));
			}
		}

		// 交通事件统计初始化
		PageRequest pageRequest = new PageRequest();
		pageRequest.addAndCondition(Dictionary.DICTYPE, SearchCondition.EQUAL, DICTIONARY_TYPE.INCIDENT_TYPE.name());
		List<Dictionary> dictionaries = dictionaryService.findAll(pageRequest);
		for (Dictionary dictionary : dictionaries) {
			PageRequest pageRequestGetCount = new PageRequest();
			pageRequestGetCount.addAndCondition(Dictionary.DICTYPE, SearchCondition.EQUAL,
					DICTIONARY_TYPE.INCIDENT_COUNT.name());
			pageRequestGetCount.addAndCondition(Dictionary.CODE, SearchCondition.EQUAL, dictionary.getCode());
			if (dictionaryService.getCount(pageRequestGetCount) == 0) {
				dictionaryService
						.save(new Dictionary(dictionary.getCode(), "0", DICTIONARY_TYPE.INCIDENT_COUNT.name()));
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// 销毁资源
		log.info("[销毁应用" + servletContextEvent.getServletContext().getServletContextName() + "结束时临时数据]");
	}
}
