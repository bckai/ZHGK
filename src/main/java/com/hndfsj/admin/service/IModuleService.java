package com.hndfsj.admin.service;

import java.util.List;

import com.hndfsj.admin.domain.Module;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.security.bean.RWUserDetails;
/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:58:21
 * @see com.hndfsj.admin.service.Module
 */
public interface IModuleService extends IBaseService<Module, java.lang.String> {
	void validateEntity(Module module)throws ValidateParamException;

	/**
	 * 根据ID的值查找该模块的子节点
	 * 
	 * @param parentId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<Module> getModuleByParentId(String parentId);

	/**
	 * 得到所有的模块资源
	 * 
	 * @return
	 */
	List<Module> getAllModuleResource();

	/**
	 * 根据登陆用户的Id得到用户可以访问的模块信息
	 * 
	 * @param userDetail
	 * 
	 * @return List<Module>
	 */
	List<Module> getModuleByUser(RWUserDetails userDetail);

	/**
	 * 获取非叶子节点
	 * 
	 * @return
	 */
	List<Module> findNotLeaf();

	// 2013年1月4日
	/**
	 * 获取用户权限模块（二级菜单使用）
	 * 
	 * @param user
	 * @param parentId
	 * @return
	 * @author Mr.Hao
	 * @version 2013-1-4 上午11:45:23
	 */
	List<Module> getModuleByUserAndParent(RWUserDetails user, String parentId);

	/**
	 * 获取用户根权限模块（一级菜单使用）
	 * 
	 * @param user
	 * @return
	 * @author Mr.Hao
	 * @version 2013-1-4 上午11:45:41
	 */
	List<Module> getRootModuleByUser(RWUserDetails user);
}
