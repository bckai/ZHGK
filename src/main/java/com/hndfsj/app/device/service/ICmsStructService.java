package com.hndfsj.app.device.service;

import java.util.List;

import com.hndfsj.app.device.domain.CmsStruct;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2017-09-12 14:44:18
 * @see com.hndfsj.app.device.service.CmsStruct
 */
public interface ICmsStructService extends IBaseService<CmsStruct, java.lang.String> {
	void validateEntity(CmsStruct cmsStruct)throws ValidateParamException;

	int isTableExists(CmsStruct cmsStruct);

	 List<CmsStruct>  getAllPage(PageRequest pageRequest);

	void updateSendState(String dvcId);

	void saveLog(List<CmsStruct> msgs);
}
