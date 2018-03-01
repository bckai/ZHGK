package com.hndfsj.admin.service;

import com.hndfsj.admin.domain.MobileErrorDetails;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:58:12
 * @see com.hndfsj.admin.service.MobileErrorDetails
 */
public interface IMobileErrorDetailsService extends IBaseService<MobileErrorDetails, java.lang.String> {
	void validateEntity(MobileErrorDetails mobileErrorDetails)throws ValidateParamException;
}
