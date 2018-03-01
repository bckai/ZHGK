package com.hndfsj.app.road.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.hndfsj.app.road.dao.IRoadFacilitiesDao;
import com.hndfsj.app.road.domain.RoadFacilities;
import com.hndfsj.framework.base.dao.BaseDaoImpl;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2018-02-26 16:31:04
 * @see com.hndfsj.road.dao.RoadFacilities
 */
@Repository("roadFacilitiesDao")
public class RoadFacilitiesDaoImpl extends BaseDaoImpl<RoadFacilities, java.lang.String>implements IRoadFacilitiesDao {

	//concstructor

	public RoadFacilitiesDaoImpl(){
		super(RoadFacilities.class);
	}

}
