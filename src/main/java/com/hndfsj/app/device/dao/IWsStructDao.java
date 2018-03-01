package com.hndfsj.app.device.dao;

import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.pager.PageRequest;

import java.util.List;

import com.hndfsj.app.device.domain.VdStruct;
import com.hndfsj.app.device.domain.WsStruct;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-09-05 14:27:53
 * @see com.hndfsj.app.device.dao.WsStruct
 */
public interface IWsStructDao extends IBaseDao<WsStruct, String> {
	void insertTable(WsStruct wsStruct);

	List<WsStruct> getAllPage(PageRequest pageRequest);
	
	int isTableExists(WsStruct wsStruct);

	WsStruct getByOneId(PageRequest pageRequest);
	
	WsStruct status(String dvcId);
}
