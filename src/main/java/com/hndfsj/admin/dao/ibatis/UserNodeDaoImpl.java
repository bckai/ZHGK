package com.hndfsj.admin.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.hndfsj.admin.dao.IUserNodeDao;
import com.hndfsj.admin.domain.UserNode;
import com.hndfsj.framework.base.dao.BaseDaoImpl;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2017-05-02 19:28:38
 * @see com.hndfsj.admin.dao.UserNode
 */
@Repository("userNodeDao")
public class UserNodeDaoImpl extends BaseDaoImpl<UserNode, java.lang.String>implements IUserNodeDao {

	//concstructor

	public UserNodeDaoImpl(){
		super(UserNode.class);
	}


}
