package com.hndfsj.app.road.service;

import com.hndfsj.app.road.domain.Road;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2018-02-26 16:31:12
 * @see com.hndfsj.road.service.Road
 */
public interface IRoadService extends IBaseService<Road, java.lang.String> {
	void validateEntity(Road road)throws ValidateParamException;
}
