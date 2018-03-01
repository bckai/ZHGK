package com.hndfsj.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.hndfsj.admin.dao.IDeptDao;
import com.hndfsj.admin.dao.IUserDao;
import com.hndfsj.admin.domain.Dept;
import com.hndfsj.admin.domain.User;
import com.hndfsj.admin.service.IDeptService;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.framework.pager.SearchCondition;

/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:55:12
 * @see com.hndfsj.admin.service.impl.Dept
 */
@Service("deptService")
public class DeptServiceImpl extends BaseServiceImpl<Dept,  java.lang.String > implements IDeptService {

	static Logger log=LoggerFactory.getLogger(DeptServiceImpl.class);

	@Resource
	private IDeptDao deptDao;
	@Resource
	private IUserDao userDao;
	@Override
	protected IBaseDao<Dept , java.lang.String> getBaseDao() {
		return this.deptDao;
	}
	public void validateEntity(Dept dept)throws ValidateParamException {
		//TODO write validate code throw new ValidateParamException
	}
	@Override
	public List<Dept> getDeptBySuperId(String parentId) {
		PageRequest pageRequest = new PageRequest();
		pageRequest.addAndCondition("superDep", SearchCondition.EQUAL, parentId);
		return deptDao.findAll(pageRequest);
	}

	@Override
	public List<Dept> getDeptsBySuperId(String deptId, Integer step) {
		return deptDao.getDeptBySuperId(deptId);
	}

	@Override
	public void updateDeptManager(Dept dept) {
		deptDao.updateDeptManager(dept);
	}

	@Override
	public String getUserIdString(String deptId, int step) {
		String userIdStr = "";
		if (step == 0) {
			// 获得本级和下级人员
			List<Dept> deptlist = getDeptsBySuperId(deptId, step);
			deptlist.add(deptDao.getById(deptId));
			for (Dept dept : deptlist) {
				PageRequest page = new PageRequest();
				page.addAndCondition("deptId", "=", dept.getId());
				List<User> userlist = userDao.findAll(page);
				for (User user : userlist) {
					userIdStr += "'" + user.getId() + "',";
				}
			}
		}
		if (step == 1) {
			// 获得本级人员
			PageRequest page = new PageRequest();
			page.addAndCondition("deptId", "=", deptId);
			List<User> userlist = userDao.findAll(page);
			for (User user : userlist) {
				userIdStr += "'" + user.getId() + "',";
			}
		}
		return userIdStr.substring(0, userIdStr.length() - 1);
	}

	@Override
	public Dept getDeptByMessager(String userId) {
		PageRequest pageRequest = new PageRequest();
		pageRequest.addAndCondition("messager", SearchCondition.EQUAL, userId);
		List<Dept> depts = deptDao.findAll(pageRequest);
		if (!depts.isEmpty())
			return depts.get(0);
		return null;
	}

	@Override
	public List<Dept> getDeptMember(PageRequest pageRequest) {
		return deptDao.getDeptMember(pageRequest);
	}

	@Override
	public Dept getByMemberId(String memberId) {
		return deptDao.getByMemberId(memberId);
	}

	@Override
	@CacheEvict(value = "deptInLocalInfo",allEntries=true)
	public void deleteById(String id) {

		super.deleteById(id);
	}

	@Override
	public String getFullDeptId(String deptId) {
		Dept dept = deptDao.getById(deptId);
		if (dept != null) {
			// 去上级部门查询
			if (dept.getSuperDep() != null) {
				String temp = getFullDeptId(dept.getSuperDep());
				if (temp != null) {
					return deptId + "-" + getFullDeptId(dept.getSuperDep());
				} else {
					return deptId + "-";
				}
			}
		}
		return null;
	}

	@Override
	@CacheEvict(value = "deptInLocalInfo",allEntries=true)
	public void save(Dept dept) {
		deptDao.save(dept);
	}


	@Override
	@CacheEvict(value = "deptInLocalInfo",allEntries=true)
	public void update(Dept dept) {
		deptDao.update(dept);
	}

	@Override
	public List<Dept> getSuperByDeptId(String deptId) {
		List<Dept> depts = new ArrayList<Dept>();
		for (;;) {
			Dept dept = deptDao.getById(deptId);
			if (dept == null) {
				break;
			}
			depts.add(dept);
			deptId = dept.getSuperDep();
		}
		return depts;
	}


	/**
	 * 获取所有子级部门
	 */
	@Override
	public List<Dept> getSubDept(String superId) {
		List<Dept> depts = new ArrayList<Dept>();
		List<Dept> list = deptDao.getDeptBySuperId(superId);
		if (list != null) {
			depts.addAll(list);
			for (Dept dept : list) {
				if (dept != null) {
					depts.addAll(getSubDept(dept.getId()));
				}
			}
		}
		return depts;
	}

	@Override
	public List<Dept> getDeptMobileMember(PageRequest pageRequest) {
		return deptDao.getDeptMobileMember(pageRequest);
	}
	@Override
	public void updateSort(List<Dept> depts) {
		for (Dept dept : depts) {
			deptDao.updateSort(dept);
		}
		
	}

	
}
