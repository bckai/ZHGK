package com.hndfsj.admin.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hndfsj.admin.dao.IGroupDao;
import com.hndfsj.admin.domain.Group;
import com.hndfsj.admin.service.IGroupService;
import com.hndfsj.framework.base.dao.IBaseDao;
import com.hndfsj.framework.base.service.BaseServiceImpl;
import com.hndfsj.framework.exceptions.ValidateParamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:55:58
 * @see com.hndfsj.admin.service.impl.Group
 */
@Service("groupService")
public class GroupServiceImpl extends BaseServiceImpl<Group,  java.lang.String > implements IGroupService {

	static Logger log=LoggerFactory.getLogger(GroupServiceImpl.class);

	@Resource
	private IGroupDao groupDao;
	
	@Override
	protected IBaseDao<Group , java.lang.String> getBaseDao() {
		return this.groupDao;
	}
	public void validateEntity(Group group)throws ValidateParamException {
		//TODO write validate code throw new ValidateParamException
	}
	
	
}
