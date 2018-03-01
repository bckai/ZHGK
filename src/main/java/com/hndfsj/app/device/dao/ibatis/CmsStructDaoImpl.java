package com.hndfsj.app.device.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hndfsj.framework.base.dao.BaseDaoImpl;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.app.device.dao.ICmsStructDao;
import com.hndfsj.app.device.domain.CmsStruct;

/**
 * TODO 
 * @copyright {@link www.hndfsj.com}
 * @author ZhengXingJian<Auto generate>
 * @version  2017-09-12 14:44:18
 * @see com.hndfsj.app.device.dao.CmsStruct
 */
@Repository("cmsStructDao")
public class CmsStructDaoImpl extends BaseDaoImpl<CmsStruct, java.lang.String>implements ICmsStructDao {

	//concstructor

	public CmsStructDaoImpl(){
		super(CmsStruct.class);
	}

	@Override
	public int isTableExists(CmsStruct cmsStruct) {
		// TODO Auto-generated method stub
		return getCountTable("isTableExists", cmsStruct);
	}

	@Override
	public List<CmsStruct> getAllPage(PageRequest pageRequest) {
		return queryList("getAllPage",pageRequest);
	}

	@Override
	public void updateSendState(String dvcId) {
		update("updateSendState", dvcId);
	}

	@Override
	public void insertTable(CmsStruct cmsStruct) {
		insert("insertTable", cmsStruct);
	}

}
