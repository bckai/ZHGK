package com.hndfsj.app.trafficevent.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.app.trafficevent.dao.ITrafficEventCommentDao;
import com.hndfsj.app.trafficevent.domain.TrafficEventComment;
import com.hndfsj.app.trafficevent.service.ITrafficEventCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 交通事件评论
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-10-17 16:53:19
 * @see com.hndfsj.app.trafficevent.service.impl.TrafficEventComment
 */
@Service("trafficEventCommentService")
public class TrafficEventCommentServiceImpl extends BaseServiceImpl<TrafficEventComment,  java.lang.String > implements ITrafficEventCommentService {

	static Logger log=LoggerFactory.getLogger(TrafficEventCommentServiceImpl.class);

	@Resource
	private ITrafficEventCommentDao trafficEventCommentDao;
	
	@Override
	protected IBaseDao<TrafficEventComment , java.lang.String> getBaseDao() {
		return this.trafficEventCommentDao;
	}
	public void validateEntity(TrafficEventComment trafficEventComment)throws ValidateParamException {
	}
	@Override
	public void deleteByEventid(String eventid) {
		trafficEventCommentDao.deleteByEventid(eventid);;
	}
	
	
}
