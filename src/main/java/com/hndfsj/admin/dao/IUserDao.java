package com.hndfsj.admin.dao;

import java.util.Date;
import java.util.List;

import com.hndfsj.admin.domain.User;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.pager.PageRequest;

/**
 * 用户Dao接口类
 * 
 * @author ibm
 * @date May 18, 2010
 */
public interface IUserDao extends IBaseDao<User, String> {

	/**
	 * 
	 * @DESC{得到用户的总记录数
	 * 
	 * @return
	 */
	Integer getUserCount();

	/**
	 * 
	 * @DESC{根据部门ID得到下边所有人员
	 * 
	 * @param deptId
	 * @return
	 */
	List<User> getUsersByDeptId(String deptId);

	/**
	 * 
	 * @DESC{根据资源ID得到所有关联的人员
	 * 
	 * @param resId
	 * @return
	 */
	List<User> getUsersByResId(String resId);

	/**
	 * 
	 * @DESC{根据角色ID得到关联的所有的用户
	 * 
	 * @param roleId
	 * @return
	 */
	List<User> getUsersByRoleId(String roleId);

	/**
	 * 修改用户密码
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	void updateUserPassword(String id, String password);

	/**
	 * 更新登陆时间
	 * 
	 * @param id
	 * @param loginTime
	 * @return
	 */
	void updateUserLoginTime(String id, Date loginTime);

	/**
	 * 
	 * @DESC{根据用户名维护个人信息
	 * 
	 * @param user
	 */
	void updateOwnInfo(User user);

	/**
	 * 根据UserName登陆账号查找用户的信息
	 * 
	 * @param userName
	 * @return
	 */
	User getUserByUserName(String userName);

	/**
	 * 根据用户id得到对应的角色信息
	 * 
	 * @param userId
	 * @return
	 */
	User getUserRoleByUserId(String userId);

	/**
	 * 根据用户ID删除用户角色信息
	 * 
	 * @param userId
	 * @return
	 */
	void deleteUserRoleByUserId(String userId);

	/**
	 * 添加人员角色信息
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	void insertUserRole(String userId, String roleId);

	/**
	 * 添加用户的资源信息
	 * 
	 * @param userId
	 * @param resourceId
	 * @return
	 */
	void insertUserResource(String userId, String resourceId);

	/**
	 * 根据用户ID删除用户资源信息
	 * 
	 * @param userId
	 * @return
	 */
	void deleteResourceByUserId(String userId);

	/**
	 * 
	 * 得到系统管理用户（UserType为0和1的user用户,1信息办用户，0超级管理员）
	 * 
	 * @return
	 * @author ibm
	 */
	List<User> getSystemUserAll();

	/**
	 * 
	 * 得到单位注册用户
	 * 
	 * @return
	 * @author ibm
	 */
	List<User> getUnitUserAll();

	/**
	 * 
	 * 根据流程ID和环节ID得到所有的UserId(执行者)
	 * 
	 * @return
	 * @author zhengwenquan
	 */
	List<User> getUsersByProcessActivity(String processId, String activityId);

	/**
	 * 
	 * 更新用户的状态
	 * 
	 * @param id
	 * @param isValid
	 */
	void updateIsValid(String id, String isValid);

	/**
	 * 
	 * 根据用户ID获得该用户所属部门的所有用户
	 * 
	 * @param id
	 * 
	 */
	List<User> getDeptUsersByUserId(String id);

	/**
	 * 
	 * id是多个用户id的拼写，可以搜索多个属部门的所有用户
	 * 
	 * @param id
	 * 
	 */
	List<User> getChildrenUsersByDeptId(String id);

	/**
	 * 根据用户组id得到分配的用户信息
	 * 
	 * @param groupId
	 * @return
	 */
	List<User> getUserByGroupId(String groupId);
	
	
	/**
	 * 通讯录特定查询
	 * author @harry
	 * 2014-9-29下午03:33:29 
	 *
	 *@Return List<User>
	 */
	List<User> findAllBySupDep(PageRequest page);

	void updateMobileInfo(User user);

	List<User> findAllRoleUser(PageRequest pageRequest);

	/**
	 * 
	 * 获取某个部门下的某个角色的人员
	 *
	 * @param roleId
	 * @param deptId
	 * @return
	 */
	List<User> getRoleUserByDeptId(String roleId, String deptId);

	List<User> getAllUsersBySupDeptId(String deptId);

	void deleteUserRoleByUserId(String userId, String roleId);

	List<User> getRoleUserByScopeDeptId(String roleId, String baseDeptId);

	void updateSort(User user);


	List<User> getManagedUser(PageRequest pageRequest);

}
