package com.hndfsj.admin.dao.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hndfsj.admin.dao.IUserDao;
import com.hndfsj.admin.domain.Role;
import com.hndfsj.admin.domain.User;
import com.hndfsj.framework.base.dao.BaseDaoImpl;
import com.hndfsj.framework.config.EnumType;
import com.hndfsj.framework.pager.PageModel;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;

/**
 * 用户Dao实现类
 * 
 * @author ibm
 * @date May 18, 2010
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, String> implements IUserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public List<User> findAll(PageRequest pageRequest) {
		pageRequest.addAndCondition("userType", SearchCondition.NOTEQUAL, EnumType.UserType.ADMIN.getUserType());
		return super.findAll(pageRequest);
	}

	@Override
	public PageModel findPageAll(PageRequest pageRequest) {
		pageRequest.addAndCondition("userType", SearchCondition.NOTEQUAL, EnumType.UserType.ADMIN.getUserType());
		return super.findPageAll(pageRequest);
	}
	@Override
	public List<User> findColumnsAll(PageRequest pageRequest) {
		pageRequest.addAndCondition("userType", SearchCondition.NOTEQUAL, EnumType.UserType.ADMIN.getUserType());
		return super.findColumnsAll(pageRequest);
	}
	@Override
	public PageModel findColumnsPageAll(PageRequest pageRequest) {
		pageRequest.addAndCondition("userType", SearchCondition.NOTEQUAL, EnumType.UserType.ADMIN.getUserType());
		return super.findColumnsPageAll(pageRequest);
	}

	@Override
	public void updateUserPassword(String id, String password) {
		User user = new User();
		user.setId(id);
		user.setPassword(password);
		update("updatePassword", user);
	}
	
	@Override
	public void updateUserLoginTime(String id, Date loginTime) {
		User user = new User();
		user.setId(id);
		user.setLoginTime(loginTime);
		update("updateLoginTime", user);
	}

	@Override
	public void updateOwnInfo(User user) {
		update("updateOwnInfo", user);
	}

	@Override
	public User getUserByUserName(String username) {
		return queryObject("getUserByUserName", username);
	}

	@Override
	public User getUserRoleByUserId(String userId) {
		return queryObject("getUserRoleByUserId", userId);
	}

	@Override
	public void deleteUserRoleByUserId(String userId) {
		delete("deleteUserRoleByUserId", userId);
	}

	@Override
	public void insertUserRole(String userId, String roleId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		insert("insertUserRole", map);
	}

	@Override
	public void deleteResourceByUserId(String userId) {
		delete("deleteResourceByUserId", userId);
	}

	@Override
	public void insertUserResource(String userId, String resourceId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("resourceId", resourceId);
		insert("insertUserResource", map);
	}

	@Override
	public Integer getUserCount() {
		return getCount();
	}

	@Override
	public List<User> getUsersByDeptId(String deptId) {
		return queryList("getUsersByDeptId", deptId);
	}

	@Override
	public List<User> getUsersByResId(String resId) {
		return queryList("getUsersByResId", resId);
	}
	@Override
	public List<User> getAllUsersBySupDeptId(String deptId) {
		
		return queryList("getAllUsersBySupDeptId", deptId);
	}
	@Override
	public List<User> getUsersByRoleId(String roleId) {
		return queryList("getUsersByRoleId", roleId);
	}

	@Override
	public List<User> getSystemUserAll() {
		return queryList("getSystemUserAll");
	}

	@Override
	public List<User> getUnitUserAll() {
		return queryList("getUnitUserAll");
	}

	@Override
	public List<User> getUsersByProcessActivity(String processId, String activityId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("processId", processId);
		map.put("activityId", activityId);
		return queryList("getUsersByProcessActivity", map);
	}

	@Override
	public void updateIsValid(String id, String isValid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("isValid", isValid);
		update("updateIsValid", map);
	}
	
	@Override
	public List<User> getDeptUsersByUserId(String id){
		return queryList("getDeptUsersByUserId",id);
	}
	
	@Override
	public List<User> getChildrenUsersByDeptId(String id){
		return queryList("getChildrenUsersByDeptId",id);
	}

	@Override
	public List<User> getUserByGroupId(String groupId) {
		return queryList("getUserByGroupId",groupId);
	}


	/* (non-Javadoc)
	 * @see com.hndfsj.admin.dao.IUserDao#findAllBySupDep(com.hndfsj.framework.pager.PageRequest)
	 */
	@Override
	public List<User> findAllBySupDep(PageRequest page) {
		//2014-9-29下午03:35:23
//		return findAllBySupDep(page);
		return queryList("findAllBySupDep",page);
	}

	@Override
	public void updateMobileInfo(User user) {
		update("updateMobileInfo", user);
	}

	@Override
	public List<User> findAllRoleUser(PageRequest pageRequest) {
		return queryList("findAllRoleUser", pageRequest);
	}

	@Override
	public List<User> getRoleUserByDeptId(String roleId, String deptId) {
		PageRequest pageRequest = getRoleUser(roleId);
		pageRequest.addAndCondition("deptId", SearchCondition.EQUAL, deptId);
		return queryList("getRoleUserByDeptId", pageRequest);
	}
	@Override
	public List<User> getRoleUserByScopeDeptId(String roleId, String deptId) {
		PageRequest pageRequest = getRoleUser(roleId);
		pageRequest.addAndCondition("scopeDeptId", SearchCondition.EQUAL, deptId);
		return queryList("getRoleUserByDeptId", pageRequest);
	}

	private PageRequest getRoleUser(String roleId) {
		PageRequest pageRequest=new PageRequest();
		pageRequest.addAndCondition("isValid", SearchCondition.EQUAL, "1");
		pageRequest.addAndCondition("roleId", SearchCondition.EQUAL, roleId);
		return pageRequest;
	}

	@Override
	public void deleteUserRoleByUserId(String userId, String roleId) {
		delete("deleteUserRoleByUserAndRoleId", new Role(userId, roleId, ""));
	}

	@Override
	public void updateSort(User user) {
		update("updateSort", user);
	}

	@Override
	public List<User> getManagedUser(PageRequest pageRequest) {
		return queryList("getManagedUser",pageRequest);
	}

}
