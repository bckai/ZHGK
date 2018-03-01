package com.hndfsj.admin.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hndfsj.admin.dao.IDeptDao;
import com.hndfsj.admin.domain.Dept;
import com.hndfsj.framework.base.dao.BaseDaoImpl;
import com.hndfsj.framework.pager.PageRequest;

/**
 * 部门Dao实现类
 * 
 * @author ibm
 * @date May 18, 2010
 */
@Repository("deptDao")
public class DeptDaoImpl extends BaseDaoImpl<Dept, String> implements IDeptDao {

	public DeptDaoImpl() {
		super(Dept.class);
	}

	@Override
	public void updateDeptManager(Dept dept) {
		update("updateDeptManager", dept);
	}

	@Override
	public List<Dept> getDeptBySuperId(String SuperId) {
		return queryList("getDeptBySuperId", SuperId);
	}

	@Override
	public List<Dept> getDeptMember(PageRequest pageRequest) {
		return queryList("getDeptMember", pageRequest);
	}
	@Override
	public List<Dept> getDeptMobileMember(PageRequest pageRequest) {
		return queryList("getDeptMobileMember", pageRequest);
	}

	@Override
	public Dept getByMemberId(String memberId) {

		return queryObject("getByMemberId", memberId);
	}

	@Override
	public Integer villageCountByDeptId(String deptId) {
		return getCountForM("villageCountByDeptId", deptId);
	}

	@Override
	public void updateVillageCountBysuperDep(String regionLevel) {
		update("updateVillageCountBysuperDep", regionLevel);
	}

	@Override
	public void updateVillageCountById(String id) {
		update("updateVillageCountById", id);
	}

	@Override
	public List<Dept> getMycreateDept(PageRequest pageRequest) {
		return queryList("getMycreateDept", pageRequest);
	}

	@Override
	public void updateSort(Dept dept) {
		 update("updateSort", dept);
	}

}
