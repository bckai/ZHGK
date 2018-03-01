package com.hndfsj.admin.service;

import java.util.Date;
import java.util.List;

import com.hndfsj.admin.domain.User;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 15:14:57
 * @see com.hndfsj.admin.service.User
 */
public interface IUserService extends IBaseService<User, java.lang.String> {
	void validateEntity(User user)throws ValidateParamException;

	/**
	 * 根据用户名称修改用户密码
	 * 
	 * @param userId
	 * @param newPassword
	 * @return
	 */
	void updateUserPassword(String userId, String newPassword);

	/**
	 * 更新登陆时间
	 * 
	 * @param userName
	 * @param LoginTime
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
	 * @param username
	 * @return
	 */
	User getUserByUserName(String username);

	/**
	 * 根据用户id得到用户对应的角色
	 * 
	 * @param userId
	 * @return
	 */
	User getRoleByUserId(String userId);

	/**
	 * 根据用户id删除用户角色管理信息
	 * 
	 * @param userId
	 * @return
	 */
	void deleteRoleByUserId(String userId);

	/**
	 * 给用户分配角色
	 * 
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	void insertUserRole(String userId, String roleIds);

	/**
	 * 给用户分配资源信息
	 * 
	 * @param userId
	 * @param resourceIds
	 * @return
	 */
	void insertUserResource(String userId, String resourceIds);

	/**
	 * 
	 * @DESC{根据部门ID得到下边所有人员（包括下属部门）
	 * 
	 * @param deptId
	 * @return
	 */
	List<User> getAllUsersByDeptId(String deptId);
	/**
	 * 
	 * @DESC{根据部门ID得到下边所有人员（当前部门）
	 * 
	 * @param deptId
	 * @return
	 */
	List<User> getUsersByDeptId(String deptId);

	/**
	 * 
	 * @DESC{根据资源ID得到关联的所有的用户
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
	 * 
	 * 得到系统管理用户（UserType为0和1的user用户,1信息办用户，0超级管理员）
	 * 
	 * @return
	 * @author ibm
	 */
	List<User> getSystemUserAll();

	/**
	 * 得到单位注册用户
	 * 
	 * @return
	 * @author wfq
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
	* 根据用户ID获得该用户所属部门的所有用户（不包括本身）
	*
	* @param userId
	* @return
	* @author wfq
	*/
	List<User>  getDeptUsersByUserId(String userId);
	
	/**
	* 根据用户ID获得该用户所属部门的所有用户
	*
	* @param userId
	* @return
	* @author wfq
	*/
	List<User>  getAllDeptUsersByUserId(String userId);

	/**
	* TODO 根据部门ID查询该部门下符合该角色的人员列表
	* @param roleId 角色ID
	* @param deptId 部门ID
	* @return
	* @author Mr.Zheng
	* @version 2015年9月18日 下午2:17:38
	*/
	List<User> getRoleUserByDeptId(String roleId, String deptId);


	List<User> getAllUsersBySupDeptId(String deptId);

	void saveRole(User user, String roleIds, String nodeIds);

	void updateRoles(User user, String roleIds, String nodeIds);

	void updateSort(List<User> users);

	List<User> getManagedUser(PageRequest pageRequest);
}
