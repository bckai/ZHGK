package com.hndfsj.app.device.service;

import java.util.List;

import com.hndfsj.app.device.domain.WsStruct;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.framework.pager.PageRequest;
/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-09-05 14:27:53
 * @see com.hndfsj.app.device.service.WsStruct
 */
public interface IWsStructService extends IBaseService<WsStruct, String> {
	void validateEntity(WsStruct wsStruct)throws ValidateParamException;
	
	List<WsStruct> getAllPage(PageRequest pageRequest);
	
	int isTableExists(WsStruct wsStruct);
	WsStruct getByOneId(PageRequest pageRequest);
	
	WsStruct status(String dvcId);
}
