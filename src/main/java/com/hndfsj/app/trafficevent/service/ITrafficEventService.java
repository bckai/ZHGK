package com.hndfsj.app.trafficevent.service;

import com.hndfsj.app.trafficevent.domain.TrafficEvent;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-10-17 16:53:03
 * @see com.hndfsj.app.trafficevent.service.TrafficEvent
 */
public interface ITrafficEventService extends IBaseService<TrafficEvent, java.lang.String> {
	void validateEntity(TrafficEvent trafficEvent)throws ValidateParamException;
}
