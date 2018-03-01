package com.hndfsj.admin.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.admin.dao.IUserOperLogsDao;
import com.hndfsj.admin.domain.UserOperLogs;
import com.hndfsj.admin.service.IUserOperLogsService;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-11-08 12:27:36
 * @see com.hndfsj.admin.service.impl.UserOperLogs
 */
@Service("userOperLogsService")
public class UserOperLogsServiceImpl extends BaseServiceImpl<UserOperLogs,  java.lang.Long > implements IUserOperLogsService {

	static Logger log=LoggerFactory.getLogger(UserOperLogsServiceImpl.class);

	@Resource
	private IUserOperLogsDao userOperLogsDao;
	
	@Override
	protected IBaseDao<UserOperLogs , java.lang.Long> getBaseDao() {
		return this.userOperLogsDao;
	}
	public void validateEntity(UserOperLogs userOperLogs)throws ValidateParamException {
		//TODO write validate code throw new ValidateParamException
	}
	
	
}
