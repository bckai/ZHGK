package com.hndfsj.app.trafficevent.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hndfsj.app.trafficevent.dao.ITrafficEventDao;
import com.hndfsj.app.trafficevent.domain.TrafficEvent;
import com.hndfsj.framework.base.dao.BaseDaoImpl;

/**
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-10-17 16:53:03
 * @see com.hndfsj.app.trafficevent.dao.TrafficEvent
 */
@Repository("trafficEventDao")
public class TrafficEventDaoImpl extends BaseDaoImpl<TrafficEvent, java.lang.String>implements ITrafficEventDao {


	public TrafficEventDaoImpl(){
		super(TrafficEvent.class);
	}

	@Override
	public List<TrafficEvent> findAllInfo(String id) {
		return queryList("findAllInfo", id);
	}

}
