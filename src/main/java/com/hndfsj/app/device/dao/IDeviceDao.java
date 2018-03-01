package com.hndfsj.app.device.dao;

import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.pager.PageRequest;

import java.util.List;
import java.util.Map;

import com.hndfsj.app.device.domain.Device;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-09-05 11:46:13
 * @see com.hndfsj.app.device.dao.Device
 */
public interface IDeviceDao extends IBaseDao<Device, java.lang.String > {

	List<Device> findAllInfo(PageRequest pageRequest);

	void addCountOne(String name);

	List<Device> findVdYearsRecordAll(PageRequest pageRequest);

	List<Map<String, String>> deviceCollect();

}
