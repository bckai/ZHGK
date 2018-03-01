package com.hndfsj.admin.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.hndfsj.admin.dao.IUserLoginLogDao;
import com.hndfsj.admin.domain.UserLoginLog;
import com.hndfsj.framework.base.dao.BaseDaoImpl;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-11-08 09:27:24
 * @see com.hndfsj.admin.dao.UserLoginLog
 */
@Repository("userLoginLogDao")
public class UserLoginLogDaoImpl extends BaseDaoImpl<UserLoginLog, java.lang.Long>implements IUserLoginLogDao {

	//concstructor

	public UserLoginLogDaoImpl(){
		super(UserLoginLog.class);
	}

}
