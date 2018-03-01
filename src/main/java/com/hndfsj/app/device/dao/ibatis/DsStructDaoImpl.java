package com.hndfsj.app.device.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hndfsj.framework.base.dao.BaseDaoImpl;
import com.hndfsj.framework.pager.PageRequest;
import com.hndfsj.app.device.dao.IDsStructDao;
import com.hndfsj.app.device.domain.DsStruct;

/**
 * TODO
 * 
 * @copyright {@link www.hndfsj.com}
 * @author BuChunKai<Auto generate>
 * @version 2017-09-05 14:27:31
 * @see com.hndfsj.app.device.dao.DsStruct
 */
@Repository("dsStructDao")
public class DsStructDaoImpl extends BaseDaoImpl<DsStruct, String> implements IDsStructDao {

	// concstructor

	public DsStructDaoImpl() {
		super(DsStruct.class);
	}

	@Override
	public void insertTable(DsStruct dsStruct) {
		insert("insertTable", dsStruct);
	}

	@Override
	public int isTableExists(DsStruct dsStruct) {
		return getCountTable("isTableExists", dsStruct);
	}

	@Override
	public List<DsStruct> getAllPage(PageRequest pageRequest) {
		return queryList("getAllPage", pageRequest);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> deviceRate() {
		return nativeQueryList("deviceRate", "");
	}

}
