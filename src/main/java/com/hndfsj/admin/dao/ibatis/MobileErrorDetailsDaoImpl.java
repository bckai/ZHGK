package com.hndfsj.admin.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.hndfsj.admin.dao.IMobileErrorDetailsDao;
import com.hndfsj.admin.domain.MobileErrorDetails;
import com.hndfsj.framework.base.dao.BaseDaoImpl;

/**
 * TODO 在此加入类描述
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2016-10-21 13:58:12
 * @see com.hndfsj.admin.dao.MobileErrorDetails
 */
@Repository("mobileErrorDetailsDao")
public class MobileErrorDetailsDaoImpl extends BaseDaoImpl<MobileErrorDetails, java.lang.String>implements IMobileErrorDetailsDao {

	//concstructor

	public MobileErrorDetailsDaoImpl(){
		super(MobileErrorDetails.class);
	}

}
