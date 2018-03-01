package com.hndfsj.app.road.service;

import com.hndfsj.app.road.domain.Center;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2018-02-26 16:30:32
 * @see com.hndfsj.road.service.Center
 */
public interface ICenterService extends IBaseService<Center, java.lang.String> {
	void validateEntity(Center center)throws ValidateParamException;
}
