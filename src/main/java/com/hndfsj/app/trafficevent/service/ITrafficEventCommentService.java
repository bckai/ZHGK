package com.hndfsj.app.trafficevent.service;

import com.hndfsj.app.trafficevent.domain.TrafficEventComment;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-10-17 16:53:19
 * @see com.hndfsj.app.trafficevent.service.TrafficEventComment
 */
public interface ITrafficEventCommentService extends IBaseService<TrafficEventComment, java.lang.String> {
	void validateEntity(TrafficEventComment trafficEventComment)throws ValidateParamException;
	
	public void deleteByEventid(String Eventid);
}
