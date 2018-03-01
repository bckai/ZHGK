package com.hndfsj.admin.service;

import java.util.List;

import com.hndfsj.admin.domain.Resource;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:59:10
 * @see com.hndfsj.admin.service.Resource
 */
public interface IResourceService extends IBaseService<com.hndfsj.admin.domain.Resource, java.lang.String> {
	void validateEntity(Resource resource)throws ValidateParamException;
	/**
	 * 得到所有的资源信息并得到对应的模块信息
	 * 
	 * @return
	 */
	List<Resource> getResourcesForSecurity();

	/**
	 * 根据模块ID得到对应的资源信息
	 * 
	 * @param moduleId
	 * @return
	 */
	List<Resource> getResourceByModuleId(String moduleId);

	/**
	 * 根据用户ID得到给用户已经分配的模块资源信息
	 * 
	 * @param userId
	 * @return
	 */
	List<Resource> getResourceByUserId(String userId);

	/**
	 * 根据角色Id得到角色对应的资源信息
	 * 
	 * @param roleId
	 * @return
	 */
	List<Resource> getResourceByRoleId(String roleId);
	/**
	 * 获取所有资源的URL
	 * 
	 * @return
	 * @author Mr.Hao
	 * @version 2013-1-27 下午02:53:56
	 */
	List<String> findAllResourceUrl();
	
	
	/**
	 * 判断某个角色该资源
	 * author @harry
	 * 2013-7-15下午03:35:47 
	 *
	 *@Return Boolean
	 */
	Boolean getResourceIdAndRoleId(String resouceId,String roleId);
}
