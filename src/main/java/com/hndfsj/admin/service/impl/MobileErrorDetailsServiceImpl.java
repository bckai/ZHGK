package com.hndfsj.admin.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.admin.dao.IMobileErrorDetailsDao;
import com.hndfsj.admin.domain.MobileErrorDetails;
import com.hndfsj.admin.service.IMobileErrorDetailsService;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:58:12
 * @see com.hndfsj.admin.service.impl.MobileErrorDetails
 */
@Service("mobileErrorDetailsService")
public class MobileErrorDetailsServiceImpl extends BaseServiceImpl<MobileErrorDetails,  java.lang.String > implements IMobileErrorDetailsService {

	static Logger log=LoggerFactory.getLogger(MobileErrorDetailsServiceImpl.class);

	@Resource
	private IMobileErrorDetailsDao mobileErrorDetailsDao;
	
	@Override
	protected IBaseDao<MobileErrorDetails , java.lang.String> getBaseDao() {
		return this.mobileErrorDetailsDao;
	}
	public void validateEntity(MobileErrorDetails mobileErrorDetails)throws ValidateParamException {
		//TODO write validate code throw new ValidateParamException
	}
	
	
}
