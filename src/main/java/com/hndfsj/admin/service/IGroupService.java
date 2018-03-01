package com.hndfsj.admin.service;

import com.hndfsj.admin.domain.Group;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:55:58
 * @see com.hndfsj.admin.service.Group
 */
public interface IGroupService extends IBaseService<Group, java.lang.String> {
	void validateEntity(Group group)throws ValidateParamException;
}
