package com.hndfsj.app.road.service;

import com.hndfsj.app.road.domain.RoadFacilities;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2018-02-26 16:31:04
 * @see com.hndfsj.road.service.RoadFacilities
 */
public interface IRoadFacilitiesService extends IBaseService<RoadFacilities, java.lang.String> {
	void validateEntity(RoadFacilities roadFacilities)throws ValidateParamException;
}
