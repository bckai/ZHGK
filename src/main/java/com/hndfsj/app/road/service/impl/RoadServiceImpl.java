package com.hndfsj.app.road.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.app.road.dao.IRoadDao;
import com.hndfsj.app.road.domain.Road;
import com.hndfsj.app.road.service.IRoadService;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2018-02-26 16:31:12
 * @see com.hndfsj.road.service.impl.Road
 */
@Service("roadService")
public class RoadServiceImpl extends BaseServiceImpl<Road,  java.lang.String > implements IRoadService {

	static Logger log=LoggerFactory.getLogger(RoadServiceImpl.class);

	@Resource
	private IRoadDao roadDao;
	
	@Override
	protected IBaseDao<Road , java.lang.String> getBaseDao() {
		return this.roadDao;
	}
	public void validateEntity(Road road)throws ValidateParamException {
		//TODO write validate code throw new ValidateParamException
	}
	
	
}
