package com.hndfsj.admin.service.impl;

import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.hndfsj.admin.dao.IRoleDao;
import com.hndfsj.admin.domain.Role;
import com.hndfsj.admin.service.IRoleService;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;

/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:59:17
 * @see com.hndfsj.admin.service.impl.Role
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role,  java.lang.String > implements IRoleService {

	static Logger log=LoggerFactory.getLogger(RoleServiceImpl.class);

	@Resource
	private IRoleDao roleDao;
	
	@Override
	protected IBaseDao<Role , java.lang.String> getBaseDao() {
		return this.roleDao;
	}
	public void validateEntity(Role role)throws ValidateParamException {
		//TODO write validate code throw new ValidateParamException
	}
	@Override
	public List<Role> getRoleUnassignByUserId(String userId) {
		return roleDao.getRoleUnassignByUserId(userId);
	}

	@Override
	public List<Role> getRoleAssignByUserId(String userId) {
		return roleDao.getRoleAssignByUserId(userId);
	}

	@Override
	public List<Role> getRolesByResId(String resId) {
		return roleDao.getRolesByResId(resId);
	}

	@Override
	@CacheEvict(value = "adminResource",allEntries=true)
	public void insertRoleResource(String roleId, String resourceIds) {
		roleDao.deleteResourceByRoleId(roleId);
		if (StringUtils.isNotEmpty(resourceIds)) {
			StringTokenizer st = new StringTokenizer(resourceIds, ",");
			while (st.hasMoreElements()) {
				if (st.hasMoreElements() == true) {
					String resourceId = (String) st.nextElement();
					if (StringUtils.isNotEmpty(resourceId)) {
						roleDao.insertRoleResource(roleId, resourceId);
					}
				}
			}
		}
	}

	@Override
	public Role getRoleByRoleName(String roleName) {
		PageRequest pageRequest = new PageRequest();
		pageRequest.addAndCondition("name", SearchCondition.EQUAL, roleName);

		List<Role> listRole = roleDao.findAll(pageRequest);
		if (listRole != null)
			return listRole.get(0);
		else
			return null;
	}

	@Override
	public List<Role> getSignRoleByUserId(String userId) {
		return roleDao.getSignRoleByUserId(userId);
	}

	
}
