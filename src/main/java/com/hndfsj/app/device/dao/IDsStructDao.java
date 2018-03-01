package com.hndfsj.app.device.dao;

import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.pager.PageRequest;

import java.util.List;
import java.util.Map;

import com.hndfsj.app.device.domain.DsStruct;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-09-05 14:27:31
 * @see com.hndfsj.app.device.dao.DsStruct
 */
public interface IDsStructDao extends IBaseDao<DsStruct, String > {
	void insertTable(DsStruct dsStruct);

	int isTableExists(DsStruct dsStruct);

	List<DsStruct> getAllPage(PageRequest pageRequest);

	List<Map<String, String>> deviceRate();
}
