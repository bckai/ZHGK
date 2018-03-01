package com.hndfsj.app.device.service;

import com.hndfsj.app.device.domain.DeviceConfig;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-09-05 11:57:01
 * @see com.hndfsj.app.device.service.DeviceConfig
 */
public interface IDeviceConfigService extends IBaseService<DeviceConfig, java.lang.String> {
	void validateEntity(DeviceConfig deviceConfig)throws ValidateParamException;
}
