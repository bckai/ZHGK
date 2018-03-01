package com.hndfsj.admin.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;
import com.hndfsj.admin.dao.IUserNodeDao;
import com.hndfsj.admin.domain.UserNode;
import com.hndfsj.admin.service.IUserNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2017-05-02 19:28:38
 * @see com.hndfsj.admin.service.impl.UserNode
 */
@Service("userNodeService")
public class UserNodeServiceImpl extends BaseServiceImpl<UserNode,  java.lang.String > implements IUserNodeService {

	static Logger log=LoggerFactory.getLogger(UserNodeServiceImpl.class);

	@Resource
	private IUserNodeDao userNodeDao;
	
	@Override
	protected IBaseDao<UserNode , java.lang.String> getBaseDao() {
		return this.userNodeDao;
	}
	public void validateEntity(UserNode userNode)throws ValidateParamException {
		//TODO write validate code throw new ValidateParamException
	}
	
	
}
