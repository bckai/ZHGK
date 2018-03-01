package com.hndfsj.app.device.service;

import java.util.List;

import com.hndfsj.app.device.domain.VdStruct;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-09-05 14:37:21
 * @see com.hndfsj.app.device.service.VdStruct
 */
public interface IVdStructService extends IBaseService<VdStruct, String> {
	void validateEntity(VdStruct vdStruct) throws ValidateParamException;

	int isTableExists(VdStruct vdStruct);

	List<VdStruct> getAllPage(PageRequest pageRequest);

	void saveWsYears(VdStruct vdStructinfo);
	
	VdStruct status(String dvcId);
}
