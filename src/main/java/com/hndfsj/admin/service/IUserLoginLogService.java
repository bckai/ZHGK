package com.hndfsj.admin.service;

import com.hndfsj.admin.domain.UserLoginLog;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-11-08 09:27:24
 * @see com.hndfsj.admin.service.UserLoginLog
 */
public interface IUserLoginLogService extends IBaseService<UserLoginLog, java.lang.Long> {
	void validateEntity(UserLoginLog userLoginLog)throws ValidateParamException;
}
