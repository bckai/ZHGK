package com.hndfsj.app.device.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.app.device.dao.ICmsShowlistDao;
import com.hndfsj.app.device.domain.CmsShowlist;
import com.hndfsj.app.device.service.ICmsShowlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-09-11 15:15:20
 * @see com.hndfsj.app.device.service.impl.CmsShowlist
 */
@Service("cmsShowlistService")
public class CmsShowlistServiceImpl extends BaseServiceImpl<CmsShowlist,  java.lang.String > implements ICmsShowlistService {

	static Logger log=LoggerFactory.getLogger(CmsShowlistServiceImpl.class);

	@Resource
	private ICmsShowlistDao cmsShowlistDao;
	
	@Override
	protected IBaseDao<CmsShowlist , java.lang.String> getBaseDao() {
		return this.cmsShowlistDao;
	}
	public void validateEntity(CmsShowlist cmsShowlist)throws ValidateParamException {
		//TODO write validate code throw new ValidateParamException
	}
	
	
}
