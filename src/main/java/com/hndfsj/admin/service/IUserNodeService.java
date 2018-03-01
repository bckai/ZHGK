package com.hndfsj.admin.service;

import com.hndfsj.admin.domain.UserNode;
import com.hndfsj.framework.base.service.IBaseService;
import com.hndfsj.framework.exceptions.ValidateParamException;
/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2017-05-02 19:28:38
 * @see com.hndfsj.admin.service.UserNode
 */
public interface IUserNodeService extends IBaseService<UserNode, java.lang.String> {
	void validateEntity(UserNode userNode)throws ValidateParamException;
}
