package com.hndfsj.app.road.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.hndfsj.app.road.dao.IRoadDao;
import com.hndfsj.app.road.domain.Road;
import com.hndfsj.framework.base.dao.BaseDaoImpl;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2018-02-26 16:31:12
 * @see com.hndfsj.road.dao.Road
 */
@Repository("roadDao")
public class RoadDaoImpl extends BaseDaoImpl<Road, java.lang.String>implements IRoadDao {

	//concstructor

	public RoadDaoImpl(){
		super(Road.class);
	}

}
