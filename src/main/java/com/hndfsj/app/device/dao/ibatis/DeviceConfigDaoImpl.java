package com.hndfsj.app.device.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.hndfsj.app.device.dao.IDeviceConfigDao;
import com.hndfsj.app.device.domain.DeviceConfig;
import com.hndfsj.framework.base.dao.BaseDaoImpl;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-09-05 11:57:01
 * @see com.hndfsj.app.device.dao.DeviceConfig
 */
@Repository("deviceConfigDao")
public class DeviceConfigDaoImpl extends BaseDaoImpl<DeviceConfig, java.lang.String>implements IDeviceConfigDao {

	//concstructor

	public DeviceConfigDaoImpl(){
		super(DeviceConfig.class);
	}

}
