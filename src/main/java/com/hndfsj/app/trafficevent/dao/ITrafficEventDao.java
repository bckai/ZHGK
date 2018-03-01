package com.hndfsj.app.trafficevent.dao;

import java.util.List;

import com.hndfsj.app.trafficevent.domain.TrafficEvent;
import com.hndfsj.framework.base.dao.IBaseDao;

/**
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-10-17 16:53:03
 * @see com.hndfsj.app.trafficevent.dao.TrafficEvent
 */
public interface ITrafficEventDao extends IBaseDao<TrafficEvent, java.lang.String > {
	public List<TrafficEvent> findAllInfo(String id);
}
