package com.hndfsj.admin.service;

import java.util.List;

import com.hndfsj.admin.domain.Role;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:59:17
 * @see com.hndfsj.admin.service.Role
 */
public interface IRoleService extends IBaseService<Role, java.lang.String> {
	void validateEntity(Role role)throws ValidateParamException;
	/**
	 * 根据角色ID得到未分配的角色信息
	 * 
	 * @param userId
	 * @return
	 */
	List<Role> getRoleUnassignByUserId(String userId);

	/**
	 * 根据角色ID得到分配的角色信息
	 * 
	 * @param userId
	 * @return
	 */
	List<Role> getRoleAssignByUserId(String userId);

	/**
	 * 
	 * @DESC{根据资源Id得到所有的角色信息
	 * 
	 * @param resId
	 * @return
	 */
	List<Role> getRolesByResId(String resId);

	/**
	 * 分配角色资源页面
	 * 
	 * @param roleId
	 * @param resourceIds
	 * @return
	 */
	void insertRoleResource(String roleId, String resourceIds);

	/**
	 * @DESC{根据角色名称获得角色信息
	 * 
	 * @param roleName
	 * @return
	 */
	Role getRoleByRoleName(String roleName);

	List<Role> getSignRoleByUserId(String userId);
}
