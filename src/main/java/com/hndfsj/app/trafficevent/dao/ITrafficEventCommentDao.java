package com.hndfsj.app.trafficevent.dao;

import com.hndfsj.app.trafficevent.domain.TrafficEventComment;
import com.hndfsj.framework.base.dao.IBaseDao;

/**
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-10-17 16:53:19
 * @see com.hndfsj.app.trafficevent.dao.TrafficEventComment
 */
public interface ITrafficEventCommentDao extends IBaseDao<TrafficEventComment, java.lang.String > {

	public void deleteByEventid(String eventid);

}
