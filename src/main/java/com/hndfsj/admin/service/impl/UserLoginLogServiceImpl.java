package com.hndfsj.admin.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.admin.dao.IUserLoginLogDao;
import com.hndfsj.admin.domain.UserLoginLog;
import com.hndfsj.admin.service.IUserLoginLogService;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-11-08 09:27:24
 * @see com.hndfsj.admin.service.impl.UserLoginLog
 */
@Service("userLoginLogService")
public class UserLoginLogServiceImpl extends BaseServiceImpl<UserLoginLog,  java.lang.Long > implements IUserLoginLogService {

	static Logger log=LoggerFactory.getLogger(UserLoginLogServiceImpl.class);

	@Resource
	private IUserLoginLogDao userLoginLogDao;
	
	@Override
	protected IBaseDao<UserLoginLog , java.lang.Long> getBaseDao() {
		return this.userLoginLogDao;
	}
	public void validateEntity(UserLoginLog userLoginLog)throws ValidateParamException {
		//TODO write validate code throw new ValidateParamException
	}
	
	
}
