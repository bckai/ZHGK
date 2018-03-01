package com.hndfsj.app.device.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.hndfsj.app.device.dao.ICmsShowlistDao;
import com.hndfsj.app.device.domain.CmsShowlist;
import com.hndfsj.framework.base.dao.BaseDaoImpl;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version  2017-09-11 15:15:20
 * @see com.hndfsj.app.device.dao.CmsShowlist
 */
@Repository("cmsShowlistDao")
public class CmsShowlistDaoImpl extends BaseDaoImpl<CmsShowlist, java.lang.String>implements ICmsShowlistDao {

	//concstructor

	public CmsShowlistDaoImpl(){
		super(CmsShowlist.class);
	}

}
