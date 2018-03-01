package com.hndfsj.app.device.service;

import java.util.List;
import java.util.Map;

import com.hndfsj.app.device.domain.Device;
import com.hndfsj.app.device.domain.DeviceConfig;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-09-05 11:46:13
 * @see com.hndfsj.app.device.service.Device
 */
public interface IDeviceService extends IBaseService<Device, java.lang.String> {
	void validateEntity(Device device) throws ValidateParamException;

	List<Device> findAllInfo(PageRequest pageRequest);

	void save(Device device, DeviceConfig deviceConfig);

	void update(Device device, DeviceConfig deviceConfig);

	/**
	 * 车检器年数据集合
	 * 
	 * @param pageRequest
	 * @return
	 */
	List<Device> findVdYearsRecordAll(PageRequest pageRequest);

	/**
	 * 设备数和各自的采集数
	 * 
	 * @return
	 */
	List<Map<String, String>> deviceCollect();

	void addCountOne(String dvcId);
}
