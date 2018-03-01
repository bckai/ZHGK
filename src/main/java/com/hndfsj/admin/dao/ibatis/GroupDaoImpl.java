package com.hndfsj.admin.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.hndfsj.admin.dao.IGroupDao;
import com.hndfsj.admin.domain.Group;
import com.hndfsj.framework.base.dao.BaseDaoImpl;

/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:55:58
 * @see com.hndfsj.admin.dao.Group
 */
@Repository("groupDao")
public class GroupDaoImpl extends BaseDaoImpl<Group, java.lang.String>implements IGroupDao {

	//concstructor

	public GroupDaoImpl(){
		super(Group.class);
	}

}
