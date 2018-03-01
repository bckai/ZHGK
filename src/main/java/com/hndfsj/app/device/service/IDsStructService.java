package com.hndfsj.app.device.service;

import java.util.List;
import java.util.Map;

import com.hndfsj.app.device.domain.DsStruct;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-09-05 14:27:31
 * @see com.hndfsj.app.device.service.DsStruct
 */
public interface IDsStructService extends IBaseService<DsStruct, String> {
	void validateEntity(DsStruct dsStruct) throws ValidateParamException;

	int isTableExists(DsStruct dsStruct);

	List<DsStruct> getAllPage(PageRequest pageRequest);

	List<Map<String, String>> deviceRate();

}
