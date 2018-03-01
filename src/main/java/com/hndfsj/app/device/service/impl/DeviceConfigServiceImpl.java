package com.hndfsj.app.device.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.app.device.dao.IDeviceConfigDao;
import com.hndfsj.app.device.domain.DeviceConfig;
import com.hndfsj.app.device.service.IDeviceConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-09-05 11:57:01
 * @see com.hndfsj.app.device.service.impl.DeviceConfig
 */
@Service("deviceConfigService")
public class DeviceConfigServiceImpl extends BaseServiceImpl<DeviceConfig,  java.lang.String > implements IDeviceConfigService {

	static Logger log=LoggerFactory.getLogger(DeviceConfigServiceImpl.class);

	@Resource
	private IDeviceConfigDao deviceConfigDao;
	
	@Override
	protected IBaseDao<DeviceConfig , java.lang.String> getBaseDao() {
		return this.deviceConfigDao;
	}
	public void validateEntity(DeviceConfig deviceConfig)throws ValidateParamException {
		//TODO write validate code throw new ValidateParamException
	}
	
	
}
