package com.hndfsj.app.trafficevent.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.hndfsj.app.trafficevent.dao.ITrafficEventCommentDao;
import com.hndfsj.app.trafficevent.domain.TrafficEventComment;
import com.hndfsj.framework.base.dao.BaseDaoImpl;

/**
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-10-17 16:53:19
 * @see com.hndfsj.app.trafficevent.dao.TrafficEventComment
 */
@Repository("trafficEventCommentDao")
public class TrafficEventCommentDaoImpl extends BaseDaoImpl<TrafficEventComment, java.lang.String>implements ITrafficEventCommentDao {


	public TrafficEventCommentDaoImpl(){
		super(TrafficEventComment.class);
	}

	@Override
	public void deleteByEventid(String eventid) {
		delete("deleteByEventid", eventid);
	}

}
