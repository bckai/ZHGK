package com.hndfsj.app.device.service;

import com.hndfsj.app.device.domain.CmsShowlist;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-09-11 15:15:20
 * @see com.hndfsj.app.device.service.CmsShowlist
 */
public interface ICmsShowlistService extends IBaseService<CmsShowlist, java.lang.String> {
	void validateEntity(CmsShowlist cmsShowlist)throws ValidateParamException;
}
