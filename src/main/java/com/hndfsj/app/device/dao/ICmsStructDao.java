package com.hndfsj.app.device.dao;

import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.pager.PageRequest;

import java.util.List;

import com.hndfsj.app.device.domain.CmsStruct;
import com.hndfsj.app.device.domain.DsStruct;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2017-09-12 14:44:18
 * @see com.hndfsj.app.device.dao.CmsStruct
 */
public interface ICmsStructDao extends IBaseDao<CmsStruct, java.lang.String > {

	int isTableExists(CmsStruct cmsStruct);

	 List<CmsStruct>  getAllPage(PageRequest pageRequest);
	 
	 void insertTable(CmsStruct cmsStruct);

	void updateSendState(String dvcId);

}
