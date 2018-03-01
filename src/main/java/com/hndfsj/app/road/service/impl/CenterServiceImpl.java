package com.hndfsj.app.road.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.app.road.dao.ICenterDao;
import com.hndfsj.app.road.domain.Center;
import com.hndfsj.app.road.service.ICenterService;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2018-02-26 16:30:32
 * @see com.hndfsj.road.service.impl.Center
 */
@Service("centerService")
public class CenterServiceImpl extends BaseServiceImpl<Center,  java.lang.String > implements ICenterService {

	static Logger log=LoggerFactory.getLogger(CenterServiceImpl.class);

	@Resource
	private ICenterDao centerDao;
	
	@Override
	protected IBaseDao<Center , java.lang.String> getBaseDao() {
		return this.centerDao;
	}
	public void validateEntity(Center center)throws ValidateParamException {
		//TODO write validate code throw new ValidateParamException
	}
	
	
}
