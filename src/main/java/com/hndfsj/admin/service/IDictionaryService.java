package com.hndfsj.admin.service;

import com.hndfsj.admin.domain.Dictionary;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:55:35
 * @see com.hndfsj.admin.service.Dictionary
 */
public interface IDictionaryService extends IBaseService<Dictionary, java.lang.String> {
	void validateEntity(Dictionary dictionary)throws ValidateParamException;
}
