package com.hndfsj.admin.dao;

import java.util.List;

import com.hndfsj.admin.domain.Role;
import com.hndfsj.framework.base.dao.IBaseDao;

/**
 * 角色接口类
 * 
 * @author ibm
 * @date May 18, 2010
 */
public interface IRoleDao extends IBaseDao<Role, String> {
	
	/**
	 * 得到该用户的流程角色
	 * author @harry
	 * 2014-7-24上午11:22:40 
	 *
	 *@Return List<Role>
	 */
	List<Role> getSignRoleByUserId(String userId);

	/**
	 * 根据用户id得到未分配的角色信息
	 * 
	 * @param userId
	 * @return
	 */
	List<Role> getRoleUnassignByUserId(String userId);

	/**
	 * 根据用户id得到分配的角色信息
	 * 
	 * @param userId
	 * @return
	 */
	List<Role> getRoleAssignByUserId(String userId);

	/**
	 * 
	 * @DESC{根据资源ID查找角色
	 * 
	 * @param resId
	 * @return
	 */
	List<Role> getRolesByResId(String resId);

	/**
	 * 删除角色对应的资源信息
	 * 
	 * @param roleId
	 * @return
	 */
	void deleteResourceByRoleId(String roleId);

	/**
	 * 添加角色对应的资源信息
	 * 
	 * @param roleId
	 * @param resourceId
	 * @return
	 */
	void insertRoleResource(String roleId, String resourceId);

}
