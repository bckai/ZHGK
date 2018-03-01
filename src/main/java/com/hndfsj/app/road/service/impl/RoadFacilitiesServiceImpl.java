package com.hndfsj.app.road.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.app.road.dao.IRoadFacilitiesDao;
import com.hndfsj.app.road.domain.RoadFacilities;
import com.hndfsj.app.road.service.IRoadFacilitiesService;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2018-02-26 16:31:04
 * @see com.hndfsj.road.service.impl.RoadFacilities
 */
@Service("roadFacilitiesService")
public class RoadFacilitiesServiceImpl extends BaseServiceImpl<RoadFacilities,  java.lang.String > implements IRoadFacilitiesService {

	static Logger log=LoggerFactory.getLogger(RoadFacilitiesServiceImpl.class);

	@Resource
	private IRoadFacilitiesDao roadFacilitiesDao;
	
	@Override
	protected IBaseDao<RoadFacilities , java.lang.String> getBaseDao() {
		return this.roadFacilitiesDao;
	}
	public void validateEntity(RoadFacilities roadFacilities)throws ValidateParamException {
		//TODO write validate code throw new ValidateParamException
	}
	
	
}
