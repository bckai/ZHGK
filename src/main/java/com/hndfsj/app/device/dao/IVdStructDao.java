package com.hndfsj.app.device.dao;

import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.pager.PageRequest;

import java.util.List;

import com.hndfsj.app.device.domain.VdStruct;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-09-05 14:37:21
 * @see com.hndfsj.app.device.dao.VdStruct
 */
public interface IVdStructDao extends IBaseDao<VdStruct, String> {
	void insertTable(VdStruct vdStruct);

	int isTableExists(VdStruct vdStruct);

	List<VdStruct> getAllPage(PageRequest pageRequest);
	
	VdStruct status(String dvcId);
}
