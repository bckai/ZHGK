package com.hndfsj.app.device.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hndfsj.framework.base.dao.BaseDaoImpl;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.app.device.dao.IDeviceDao;
import com.hndfsj.app.device.domain.Device;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-09-05 11:46:13
 * @see com.hndfsj.app.device.dao.Device
 */
@Repository("deviceDao")
public class DeviceDaoImpl extends BaseDaoImpl<Device, java.lang.String> implements IDeviceDao {

	// concstructor

	public DeviceDaoImpl() {
		super(Device.class);
	}

	@Override
	public List<Device> findAllInfo(PageRequest pageRequest) {
		return queryList("findAllInfo", pageRequest);
	}

	@Override
	public void addCountOne(String dvcId) {
		update("updateCount", new Device(dvcId));
	}

	@Override
	public List<Device> findVdYearsRecordAll(PageRequest pageRequest) {
		return queryList("findVdYearsRecordAll", pageRequest);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> deviceCollect() {
		return nativeQueryList("deviceCollect", "");
	}
}
