package com.hndfsj.admin.service;

import com.hndfsj.admin.domain.UserOperLogs;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-11-08 12:27:36
 * @see com.hndfsj.admin.service.UserOperLogs
 */
public interface IUserOperLogsService extends IBaseService<UserOperLogs, java.lang.Long> {
	void validateEntity(UserOperLogs userOperLogs)throws ValidateParamException;
}
