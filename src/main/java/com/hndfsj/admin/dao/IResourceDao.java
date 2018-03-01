package com.hndfsj.admin.dao;

import java.util.List;

import com.hndfsj.admin.domain.Resource;
import com.hndfsj.framework.base.dao.IBaseDao;

/**
 * 资源Dao接口类
 * 
 * @author ibm
 * @date May 18, 2010
 */
public interface IResourceDao extends IBaseDao<Resource, String> {

	/**
	 * 得到所有的资源信息并得到对应的模块信息
	 * 
	 * @return
	 */
	List<Resource> getResourcesForSecurity();

	/**
	 * 根据用户id得到该用户的已经分配的资源信息
	 * 
	 * @param userId
	 * @return
	 */
	List<Resource> getResourceByUserId(String userId);

	/**
	 * 根据角色Id得到对应的角色资源信息
	 * 
	 * @param roleId
	 * @return
	 */
	List<Resource> getResourceByRoleId(String roleId);
	
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
