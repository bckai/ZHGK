package com.hndfsj.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.hndfsj.admin.dao.IDeptDao;
import com.hndfsj.admin.dao.IUserDao;
import com.hndfsj.admin.dao.IUserNodeDao;
import com.hndfsj.admin.domain.Dept;
import com.hndfsj.admin.domain.User;
import com.hndfsj.admin.domain.UserNode;
import com.hndfsj.admin.service.IUserService;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.utils.CryptUtil;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version 2016-10-21 15:14:57
 * @see com.hndfsj.admin.service.impl.User
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, java.lang.String> implements IUserService {

	static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	private IUserDao userDao;
	@Resource
	private IDeptDao deptDao;
	@Resource
	private IUserNodeDao userNodeDao;

	@Override
	protected IBaseDao<User, java.lang.String> getBaseDao() {
		return this.userDao;
	}

	public void validateEntity(User user) throws ValidateParamException {
		if (StringUtils.isBlank(user.getRealname()))
			throw new ValidateParamException("realname is null");
		if (StringUtils.isBlank(user.getSex()))
			throw new ValidateParamException("sex is null");
		if (StringUtils.isBlank(user.getDeptId()))
			throw new ValidateParamException("deptId is null");
	}

	@Override
	@CacheEvict(value = "userInfo",allEntries=true)
	public void deleteById(String id) {
		super.deleteById(id);
	}

	@Override
	@CacheEvict(value = "userInfo",allEntries=true)
	public void save(User entity) {
		super.save(entity);
	}

	@Override
	@CacheEvict(value = "userInfo",allEntries=true)
	public void update(User entity) {
		super.update(entity);
	}

	@Override
	@CacheEvict(value = "userInfo",allEntries=true)
	public void updateUserPassword(String userId, String newPassword) {
		String password = CryptUtil.encoderByMd5For32Bit(newPassword);
		userDao.updateUserPassword(userId, password);
	}

	@Override
	public void updateUserLoginTime(String userName, Date loginTime) {
		userDao.updateUserLoginTime(userName, loginTime);
	}

	@Override
	@CacheEvict(value = "userInfo",allEntries=true)
	public void updateOwnInfo(User user) {
		userDao.updateOwnInfo(user);
	}

	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	@Override
	public User getRoleByUserId(String userId) {
		return userDao.getUserRoleByUserId(userId);
	}

	@Override
	@CacheEvict(value = "userInfo",allEntries=true)
	public void deleteRoleByUserId(String userId) {
		userDao.deleteUserRoleByUserId(userId);
	}

	@Override
	@CacheEvict(value = "userInfo",allEntries=true)
	public void insertUserRole(String userId, String roleIds) {
		userDao.deleteUserRoleByUserId(userId);
		if (StringUtils.isNotEmpty(roleIds)) {
			StringTokenizer st = new StringTokenizer(roleIds, ",");
			while (st.hasMoreElements()) {
				if (st.hasMoreElements() == true) {
					String roleId = (String) st.nextElement();
					if (StringUtils.isNotEmpty(roleId)) {
						userDao.insertUserRole(userId, roleId);
					}
				}
			}
		}
	}

	@Override
	@CacheEvict(value = "userInfo",allEntries=true)
	public void insertUserResource(String userId, String resourceIds) {
		userDao.deleteResourceByUserId(userId);
		if (StringUtils.isNotEmpty(resourceIds)) {
			StringTokenizer st = new StringTokenizer(resourceIds, ",");
			while (st.hasMoreElements()) {
				if (st.hasMoreElements() == true) {
					String resourceId = (String) st.nextElement();
					if (StringUtils.isNotEmpty(resourceId)) {
						userDao.insertUserResource(userId, resourceId);
					}
				}
			}
		}
	}

	@Override
	public List<User> getAllUsersByDeptId(String deptId) {
		List<String> deptIdz = new ArrayList<String>();
		Dept dept = deptDao.getById(deptId);
		if (dept != null) {
			deptIdz = getChildrenDeptId(dept.getId(), deptIdz);
			StringBuffer deptString = new StringBuffer();
			for (String newDeptId : deptIdz) {
				deptString.append("'" + newDeptId + "',");
			}
			List<User> newUser = userDao.getChildrenUsersByDeptId(deptString.toString() + "'" + dept.getId() + "'");
			return newUser;
		}
		return new ArrayList<User>();
	}

	@Override
	public List<User> getUsersByDeptId(String deptId) {
		return userDao.getUsersByDeptId(deptId);
	}

	@Override
	public List<User> getUsersByResId(String resId) {
		return userDao.getUsersByResId(resId);
	}

	@Override
	public List<User> getUsersByRoleId(String roleId) {
		return userDao.getUsersByRoleId(roleId);
	}

	@Override
	public List<User> getSystemUserAll() {
		return userDao.getSystemUserAll();
	}

	@Override
	public List<User> getUnitUserAll() {
		return userDao.getUnitUserAll();
	}

	@Override
	public List<User> getUsersByProcessActivity(String processId, String activityId) {
		return userDao.getUsersByProcessActivity(processId, activityId);
	}

	@Override
	public List<User> getDeptUsersByUserId(String userId) {
		User user = userDao.getById(userId);// 以后可能要扩充，可能要先从部门的表查询，通过manamger
		Dept dept = deptDao.getById(user.getDeptId());
		List<User> users = userDao.getDeptUsersByUserId(userId);
		List<User> users1 = new ArrayList<User>();
		users1.add(user);
		users.removeAll(users1);
		List<String> deptIdz = new ArrayList<String>();
		deptIdz = getChildrenDeptId(dept.getId(), deptIdz);
		StringBuffer deptString = new StringBuffer();
		if (deptIdz != null) {
			for (String deptId : deptIdz) {
				deptString.append(",'" + deptId + "'");
			}
			deptString.delete(0, 1);
			List<User> newUser = userDao.getChildrenUsersByDeptId(deptString.toString());
			users.addAll(newUser);
		}
		return users;
	}

	/*
	 * 1、superId 2、所有一级dept as dept1s，循环dept1s 3、循环中的每一个dept1
	 * ，查找dept1的id，根据此id去查superId 得到dept2s，dept2s的size
	 */
	public List<String> getChildrenDeptId(String SuperDep, List<String> deptIdz) {
		List<Dept> depts = deptDao.getDeptBySuperId(SuperDep);
		for (Dept newDept : depts) {
			deptIdz.add(newDept.getId());
			if (deptDao.getDeptBySuperId(newDept.getId()).size() > 0) {
				getChildrenDeptId(newDept.getId(), deptIdz);
			}
		}
		return deptIdz;
	}

	@Override
	public List<User> getAllDeptUsersByUserId(String userId) {
		Dept dept = deptDao.getById(userDao.getById(userId).getDeptId());
		List<User> users = userDao.getDeptUsersByUserId(userId);
		List<String> deptIdz = new ArrayList<String>();
		deptIdz = getChildrenDeptId(dept.getId(), deptIdz);
		StringBuffer deptString = new StringBuffer();
		if (deptIdz != null) {
			for (String deptId : deptIdz) {
				deptString.append(",'" + deptId + "'");
			}
			deptString.delete(0, 1);
			if (StringUtils.isNotBlank(deptString.toString())) {
				List<User> newUser = userDao.getChildrenUsersByDeptId(deptString.toString());
				users.addAll(newUser);
			}
		}
		return users;
	}

	@Override
	public List<User> getRoleUserByDeptId(String roleId, String deptId) {

		return userDao.getRoleUserByDeptId(roleId, deptId);
	}

	@Override
	public List<User> getAllUsersBySupDeptId(String deptId) {
		return userDao.getAllUsersBySupDeptId(deptId);
	}

	@Override
	public void saveRole(User user, String roleIds, String nodeIds) {
		try {
			userDao.save(user);
		} catch (DuplicateKeyException duplicateKeyException) {
			throw new ValidateParamException("该用户名已经存在！");
		}
		insertUserRole(user.getId(), roleIds);
		insertUserNode(user.getId(), nodeIds);
	}
	
	@CacheEvict(value = "userInfo",allEntries=true)
	private void insertUserNode(String userId, String nodeIds) {
		userNodeDao.deleteById(userId);
		if (StringUtils.isNotEmpty(nodeIds)) {
			StringTokenizer st = new StringTokenizer(nodeIds, ",");
			while (st.hasMoreElements()) {
				if (st.hasMoreElements() == true) {
					String nodeId = (String) st.nextElement();
					if (StringUtils.isNotEmpty(nodeId)) {
						userNodeDao.save(new UserNode(userId, nodeId));
					}
				}
			}
		}
		
	}

	@Override
	public void updateRoles(User user, String roleIds, String nodeIds) {
		update(user);
		insertUserRole(user.getId(), roleIds);
		//insertUserNode(user.getId(), nodeIds);
	}

	@Override
	public void updateSort(List<User> users) {
		for (User user : users) {
			userDao.updateSort(user);
		}
		
	}

	@Override
	public List<User> getManagedUser(PageRequest pageRequest) {
		return userDao.getManagedUser(pageRequest);
	}

}
