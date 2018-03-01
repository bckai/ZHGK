package com.hndfsj.admin.dao;

import java.util.List;

import com.hndfsj.admin.domain.Module;
import com.hndfsj.framework.base.dao.IBaseDao;

/**
 * 模块Dao接口类
 * 
 * @author ibm
 * @date May 18, 2010
 */
public interface IModuleDao extends IBaseDao<Module, String> {

	/**
	 * 得到所有的模块资源树
	 * 
	 * @return
	 */
	List<Module> getAllModuleResource();

	/**
	 * 得到用户已经分配权限的模块Id
	 * 
	 * @param userId
	 * @return
	 */
	List<Module> getModIdByUserId(String userId);
}
