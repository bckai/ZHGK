package com.hndfsj.admin.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hndfsj.admin.dao.IRoleDao;
import com.hndfsj.admin.domain.Role;
import com.hndfsj.framework.base.dao.BaseDaoImpl;

/**
 * 角色Dao实现类
 * 
 * @author ibm
 * @date May 18, 2010
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role, String> implements IRoleDao {

	public RoleDaoImpl() {
		super(Role.class);
	}

	@Override
	public List<Role> getRoleUnassignByUserId(String userId) {
		return queryList("getRoleUnassignByUserId", userId);
	}

	@Override
	public List<Role> getRoleAssignByUserId(String userId) {
		return queryList("getRoleAssignByUserId", userId);
	}

	@Override
	public List<Role> getRolesByResId(String resId) {
		return queryList("getRolesByResId", resId);
	}

	@Override
	public void deleteResourceByRoleId(String roleId) {
		delete("deleteResourceByRoleId", roleId);
	}

	@Override
	public void insertRoleResource(String roleId, String resourceId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("roleId", roleId);
		map.put("resourceId", resourceId);
		insert("insertRoleResource", map);
	}

	/* (non-Javadoc)
	 * @see com.hndfsj.admin.dao.IRoleDao#getSignRoleByUserId(java.lang.String)
	 */
	@Override
	public List<Role> getSignRoleByUserId(String userId) {
		//2014-7-24上午11:23:31
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		return queryList("getSignRoleByUserId", map);
	}
}
