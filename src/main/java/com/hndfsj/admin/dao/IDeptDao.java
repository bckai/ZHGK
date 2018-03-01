package com.hndfsj.admin.dao;

import java.util.List;

import com.hndfsj.admin.domain.Dept;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.pager.PageRequest;

/**
 * 部门Dao接口类
 * 
 * @author ibm
 * @date May 18, 2010
 */
public interface IDeptDao extends IBaseDao<Dept, String> {
	/**
	 * 更新部门负责人
	 * 
	 * @author lijunwei
	 *
	 */
	void updateDeptManager(Dept dept);

	/**
	 * 根据SuperId获得用户
	 * 
	 * @param SuperId
	 * @author lijunwei
	 *
	 */
	List<Dept> getDeptBySuperId(String SuperId);


	List<Dept> getDeptMember(PageRequest pageRequest);

	Dept getByMemberId(String memberId);

	Integer villageCountByDeptId(String deptId);

	void updateVillageCountBysuperDep(String regionLevel);
	void updateVillageCountById(String id);

	List<Dept> getMycreateDept(PageRequest pageRequest);

	List<Dept> getDeptMobileMember(PageRequest pageRequest);

	void updateSort(Dept dept);


}
