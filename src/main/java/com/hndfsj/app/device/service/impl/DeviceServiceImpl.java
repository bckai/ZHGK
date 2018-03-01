package com.hndfsj.app.device.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.app.device.dao.IDeviceConfigDao;
import com.hndfsj.app.device.dao.IDeviceDao;
import com.hndfsj.app.device.domain.Device;
import com.hndfsj.app.device.domain.DeviceConfig;
import com.hndfsj.app.device.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-09-05 11:46:13
 * @see com.hndfsj.app.device.service.impl.Device
 */
@Service("deviceService")
public class DeviceServiceImpl extends BaseServiceImpl<Device, java.lang.String> implements IDeviceService {

	static Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);

	@Resource
	private IDeviceDao deviceDao;
	@Resource
	private IDeviceConfigDao deviceConfigDao;

	@Override
	protected IBaseDao<Device, java.lang.String> getBaseDao() {
		return this.deviceDao;
	}

	public void validateEntity(Device device) throws ValidateParamException {
		// TODO write validate code throw new ValidateParamException
	}

	@Override
	public List<Device> findAllInfo(PageRequest pageRequest) {
		return deviceDao.findAllInfo(pageRequest);
	}

	@Override
	public void save(Device device, DeviceConfig deviceConfig) {
		deviceDao.deleteById(device.getId());
		deviceConfigDao.deleteById(deviceConfig.getDvcId());
		deviceDao.save(device);
		deviceConfigDao.save(deviceConfig);
	}

	@Override
	public void update(Device device, DeviceConfig deviceConfig) {
		deviceDao.update(device);
		deviceConfigDao.update(deviceConfig);
	}

	@Override
	public List<Device> findVdYearsRecordAll(PageRequest pageRequest) {
		return deviceDao.findVdYearsRecordAll(pageRequest);
	}

	@Override
	public List<Map<String, String>> deviceCollect() {
		return deviceDao.deviceCollect();
	}

	@Override
	public void addCountOne(String dvcId) {
		deviceDao.addCountOne(dvcId);
	}
}
