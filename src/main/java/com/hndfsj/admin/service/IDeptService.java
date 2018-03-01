package com.hndfsj.admin.service;

import java.util.List;

import com.hndfsj.admin.domain.Dept;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:55:12
 * @see com.hndfsj.admin.service.Dept
 */
public interface IDeptService extends IBaseService<Dept, java.lang.String> {
	void validateEntity(Dept dept)throws ValidateParamException;

	/**
	 * 
	 * @DESC{根据上级部门ID查找下边所有的部门
	 * 
	 * @param parentId
	 * @return
	 */
	List<Dept> getDeptBySuperId(String parentId);
	
	/**
	 * 
	 * 获得下属组织结构，根据step的深度决定获取哪一级深
	 * 
	 * @param deptId
	 * @param step
	 *            可以为0 获取所有包括下级的下级；为1获取只获取直属；为2获取到直属的下级组织结构，依次类推
	 * @return
	 */
	public List<Dept> getDeptsBySuperId(String deptId, Integer step);
	/**
	 * 
	 * 更新部门负责人
	 * 
	 * @param dept
	 * @return
	 */
	void updateDeptManager(Dept dept);

	/**
	 * 获取该部门和下属部门人员的id string
	 * @param id
	 * @return
	 */
	String getUserIdString(String deptId, int step);

	Dept getDeptByMessager(String userId);

	List<Dept> getDeptMember(PageRequest pageRequest);

	Dept getByMemberId(String memberId);

	String getFullDeptId(String deptId);

	List<Dept> getSuperByDeptId(String deptId);

	List<Dept> getSubDept(String superId);

	List<Dept> getDeptMobileMember(PageRequest pageRequest);

	void updateSort(List<Dept> depts);
}
