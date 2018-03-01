package com.hndfsj.admin.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.hndfsj.admin.dao.IUserOperLogsDao;
import com.hndfsj.admin.domain.UserOperLogs;
import com.hndfsj.framework.base.dao.BaseDaoImpl;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-11-08 12:27:36
 * @see com.hndfsj.admin.dao.UserOperLogs
 */
@Repository("userOperLogsDao")
public class UserOperLogsDaoImpl extends BaseDaoImpl<UserOperLogs, java.lang.Long>implements IUserOperLogsDao {

	//concstructor

	public UserOperLogsDaoImpl(){
		super(UserOperLogs.class);
	}

}
