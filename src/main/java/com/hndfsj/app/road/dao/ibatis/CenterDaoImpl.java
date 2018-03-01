package com.hndfsj.app.road.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.hndfsj.app.road.dao.ICenterDao;
import com.hndfsj.app.road.domain.Center;
import com.hndfsj.framework.base.dao.BaseDaoImpl;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2018-02-26 16:30:32
 * @see com.hndfsj.road.dao.Center
 */
@Repository("centerDao")
public class CenterDaoImpl extends BaseDaoImpl<Center, java.lang.String>implements ICenterDao {

	//concstructor

	public CenterDaoImpl(){
		super(Center.class);
	}

}
